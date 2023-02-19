/**
 *
 * @auther Sachin Kumar
 * @version 18-12-2022
 *
 */

#define CL_HPP_TARGET_OPENCL_VERSION 300

#include <iostream>
#include <CL/opencl.hpp>

class ClCode {
    public:
    ClCode();
    ~ClCode();
    void distanceCompare(std::size_t numberOfVehicles, int vehicleDistances[], int safetyDistances[], bool status[]);
    private:
    std::vector<cl::Platform> all_platforms;
    cl::Context *context;
    cl::Device default_device;
    cl::Program *program;
};
