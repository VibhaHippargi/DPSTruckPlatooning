#include "../include/cl_code.hpp"

ClCode::ClCode() {
cl::Platform::get(&all_platforms);
    if(all_platforms.size()==0){
        std::cout<<" No platforms found. Check OpenCL installation!\n";
        exit(1);
    }
    std::cout << "size of platforms " << all_platforms.size() << std::endl;
    cl::Platform default_platform=all_platforms[0];
    std::cout << "Using platform: "<<default_platform.getInfo<CL_PLATFORM_NAME>()<<"\n";
 
    //get default device of the default platform
    std::vector<cl::Device> all_devices;
    default_platform.getDevices(CL_DEVICE_TYPE_ALL, &all_devices);
    if(all_devices.size()==0){
        std::cout<<" No devices found. Check OpenCL installation!\n";
        exit(1);
    }
    default_device=all_devices[0];
    std::cout<< "Using device: "<<default_device.getInfo<CL_DEVICE_NAME>()<<"\n";
 
 
    context = new cl::Context({default_device});
 
    cl::Program::Sources sources;
 
    std::string kernel_code=
            "   void kernel distance_safety(global const int* vehicleDistance, global const int* safetyDistance, global bool* status){       "
            "           if(vehicleDistance[get_global_id(0)] > safetyDistance[get_global_id(0)]) {                         "
            "               status[get_global_id(0)] = true;                                            "
            "           }                                                                       "
            "           else {                                                                  "
            "               status[get_global_id(0)] = false;                                            "
            "           }                                                                       "
            "   }                                                                               ";
    sources.push_back({kernel_code.c_str(),kernel_code.length()});

 
    program = new cl::Program(*context,sources);
    if(program->build({default_device})!=CL_SUCCESS){
        std::cout<<" Error building: "<<program->getBuildInfo<CL_PROGRAM_BUILD_LOG>(default_device)<<"\n";
        exit(1);
    }
}
ClCode::~ClCode(){
    delete program;
    delete context;
}


void ClCode::distanceCompare(std::size_t numberOfVehicles, int vehicleDistances[], int safetyDistances[], bool status[]) {
    // create buffers on the device
    cl::Buffer buffer_vehicleDistances(*context,CL_MEM_READ_WRITE,sizeof(int)*numberOfVehicles);
    cl::Buffer buffer_safetyDistances(*context,CL_MEM_READ_WRITE,sizeof(int)*numberOfVehicles);
    cl::Buffer buffer_status(*context,CL_MEM_READ_WRITE,sizeof(bool)*numberOfVehicles);
 
 
    //create queue to which we will push commands for the device.
    cl::CommandQueue queue(*context,default_device);
 
    //write arrays A and B to the device
    queue.enqueueWriteBuffer(buffer_vehicleDistances,CL_TRUE,0,sizeof(int)*numberOfVehicles,vehicleDistances);
    queue.enqueueWriteBuffer(buffer_safetyDistances,CL_TRUE,0,sizeof(int)*numberOfVehicles,safetyDistances);
 
 
    //run the kernel
    cl::Kernel kernel_add=cl::Kernel(*program,"distance_safety");
    kernel_add.setArg(0,buffer_vehicleDistances);
    kernel_add.setArg(1,buffer_safetyDistances);
    kernel_add.setArg(2,buffer_status);
    queue.enqueueNDRangeKernel(kernel_add,cl::NullRange,cl::NDRange(numberOfVehicles),cl::NullRange);
    queue.finish();
 
    printf("queue finish\n");
    //read result C from the device to array C
    queue.enqueueReadBuffer(buffer_status,CL_TRUE,0,sizeof(bool)*numberOfVehicles,status);
 
}

