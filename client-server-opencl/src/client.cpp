/**
 *
 * @auther Sachin Kumar
 * @version 18-12-2022
 *
 */

#include <iostream>
#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <stdlib.h>
#define PORT 8080


class Client {
public:
    Client() {
        sock = 0;
        hello = "Hello from client";
        if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
            printf("\n Socket creation error \n");
            exit(-1);
        }

        serv_addr.sin_family = AF_INET;
        serv_addr.sin_port = htons(PORT);

    }
    ~Client() {
        // closing the connected socket
        close(client_fd);
        // delete pointer
        // delete hello;
    }
    void run() {
        // Convert IPv4 and IPv6 addresses from text to binary
        // form
        if (inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr)
            <= 0) {
            printf(
                "\nInvalid address/ Address not supported \n");
            exit(-1);
        }

        if ((client_fd = connect(sock, (struct sockaddr*)&serv_addr,
				sizeof(serv_addr)))
		< 0) {
		printf("\nConnection Failed \n");
		exit(1);
	}
        int numberOfVehicles;
        std::cout << "Number of vehicles: ";
        std::cin >> numberOfVehicles;


        // std::string vehicleDistancesStr = "";

        hello = (char *) std::to_string(numberOfVehicles).c_str();
        send(sock, hello, strlen(hello), 0);
        int distance;
        for(std::size_t vehicleId = 0; vehicleId < numberOfVehicles; vehicleId++) {
            valread = read(sock, buffer, 1024);
            printf("%s",buffer);
            std::cout << "\nEnter distance for vehicle " << vehicleId <<": ";
            std::cin >> distance;
            hello = (char *) std::to_string(distance).c_str();
            send(sock, hello, strlen(hello), 0);
        }
        bool status[numberOfVehicles];

        for(std::size_t vehicleId = 0; vehicleId < numberOfVehicles; vehicleId++) {
            valread = read(sock, buffer, 1024);
            status[vehicleId] = atoi(buffer); 
            hello = "ok";
            send(sock, hello, strlen(hello), 0);
        }

        std::string speed;
        for(std::size_t vehicleId = 0; vehicleId < numberOfVehicles; vehicleId++) {

            speed = (status[vehicleId]) ? "Decelerate." : "Accelerate.";

            std::cout << "Vehicle " << vehicleId << " should " << speed << std::endl;
        }
    }
private:
	int sock, valread, client_fd;
	struct sockaddr_in serv_addr;
	char* hello;
	char buffer[1024] = { 0 };

};

int main(int argc, char const* argv[])
{
    Client clientSocket = Client();
    clientSocket.run();
	return 0;
}
