CC = g++

CFLAGS = -g -std=c++17 -Wall -pedantic


all:server client

server: src/server.cpp
	$(CC) $(CFLAGS) -o bin/server.out src/server.cpp src/cl_code.cpp -I ./include -lOpenCL

client: src/client.cpp
	$(CC) $(CFLAGS) -o bin/client.out src/client.cpp -I ./include


clean:
	$(RM) bin/cl_code.out bin/server.out bin/client.out

run_server:
	./bin/server.out

run_client:
	./bin/client.out
