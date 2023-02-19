/**
 *
 * @auther Sachin Kumar
 * @version 18-12-2022
 *
 */

#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include "../include/cl_code.hpp"
#define PORT 8080

class Server {
public:
	Server() {
		clCodeObj = new ClCode();
		opt = 1;
		addrlen = sizeof(address);
		hello = "Hello from server";
		// Creating socket file descriptor
		if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
			perror("socket failed");
			exit(EXIT_FAILURE);
		}

		printf("server is listening\n");
		// Forcefully attaching socket to the port 8080
		if (setsockopt(server_fd, SOL_SOCKET,
					SO_REUSEADDR | SO_REUSEPORT, &opt,
					sizeof(opt))) {
			perror("setsockopt");
			exit(EXIT_FAILURE);
		}
		address.sin_family = AF_INET;
		address.sin_addr.s_addr = INADDR_ANY;
		address.sin_port = htons(PORT);

		// Forcefully attaching socket to the port 8080
		if (bind(server_fd, (struct sockaddr*)&address,
				sizeof(address))
			< 0) {
			perror("bind failed");
			exit(EXIT_FAILURE);
		}
	}
	~Server() {
		// closing the connected socket
		close(new_socket);
		// closing the listening socket
		shutdown(server_fd, SHUT_RDWR);
		// delete pointer
		// delete hello;
		delete clCodeObj;
	}

	void run() {
		if (listen(server_fd, 3) < 0) {
			perror("listen");
			exit(EXIT_FAILURE);
		}
		if ((new_socket
			= accept(server_fd, (struct sockaddr*)&address,
					(socklen_t*)&addrlen))
			< 0) {
			perror("accept");
			exit(EXIT_FAILURE);
		}
		int numberOfVehicles;
		valread = read(new_socket, buffer, 1024);
		numberOfVehicles = atoi(buffer);
		printf("number of vehicles %d\n", numberOfVehicles);
		int vehicleDistances[numberOfVehicles], safetyDistances[numberOfVehicles];
		bool status[numberOfVehicles];
		hello = "ok";
		for (size_t vehicleId = 0; vehicleId < numberOfVehicles; vehicleId++){
			send(new_socket, hello, strlen(hello), 0);
			valread = read(new_socket, buffer, 1024);
			vehicleDistances[vehicleId] = atoi(buffer);
			printf("vehicle value received\n");
		}
		
		for (size_t vehicleId = 0; vehicleId < numberOfVehicles; vehicleId++){
			safetyDistances[vehicleId] = 50;
		}
		clCodeObj->distanceCompare(numberOfVehicles, vehicleDistances, safetyDistances, status);
		printf("got the values");
		
		for (size_t vehicleId = 0; vehicleId < numberOfVehicles; vehicleId++){
			if(status[vehicleId]) {
				hello = "1";
			}
			else {
				hello = "0";
			}
			send(new_socket, hello, strlen(hello), 0);
			valread = read(new_socket, buffer, 1024);
		}
	}

private:
	int server_fd, new_socket, valread;
	struct sockaddr_in address;
	int opt;
	int addrlen;
	char buffer[1024] = { 0 };
	char* hello;
	ClCode *clCodeObj;
};
int main(int argc, char const* argv[])
{
	Server socketServerObj = Server();
	socketServerObj.run();
	return 0;
}
