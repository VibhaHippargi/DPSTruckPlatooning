#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<unistd.h>


int main()
{

      //char server_message[256]="You have reached the server!";
      char buffer[1024];
      
      //create the server socket
      int server_socket;
      int port=9002;
      //char buffer[1024];
      server_socket=socket(AF_INET,SOCK_STREAM,0);
      if(server_socket<0)
      {
      printf("Socket error\n");
      }
      printf("TCP server socket created\n");


      //specify an address for the socket
      struct sockaddr_in server_address;
      server_address.sin_family        = AF_INET;
      server_address.sin_port          = htons(port);
      server_address.sin_addr.s_addr   = INADDR_ANY;


       //bind the socket
       int n=bind(server_socket, (struct sockaddr*) &server_address,sizeof(server_address));
       if(n<0)
       { 
       printf("Bind error");
       exit(1);
       }
       printf("Bind to the port number: %d\n",port);

     //listen
     listen(server_socket,5);
     printf("Listening...\n");

     int client_socket;
     
     while(1){
     client_socket = accept (server_socket, NULL,NULL);
     printf("Client connected\n");
     
     //send the message
     bzero(buffer,1024);
     strcpy(buffer,"You have reached the server!\n");
     send(client_socket,buffer,sizeof(buffer),0);


    bzero(buffer,1024);
    recv(client_socket,buffer,sizeof(buffer),0);
    printf("Client : %s\n",buffer);
    
    
    bzero(buffer,1024);
    strcpy(buffer,"Hello,this is server\n");
    printf("Server : %s\n",buffer);
    send(client_socket,buffer,sizeof(buffer),0);
    
     //close the socket
     close(server_socket);  
     printf("Server closed,client disconnected\n");
      return 0;
      }

}

