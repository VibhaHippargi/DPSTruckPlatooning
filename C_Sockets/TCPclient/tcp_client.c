#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<unistd.h>


int main()
{

      //create a socket
      int network_socket;
      network_socket=socket(AF_INET,SOCK_STREAM,0);
      
      char buffer[1024];

      //specify an address for the socket
      struct sockaddr_in server_address;
      server_address.sin_family        = AF_INET;
      server_address.sin_port          = htons(9002);
      server_address.sin_addr.s_addr   = INADDR_ANY;

      //connect
      int connection_status = connect(network_socket,(struct sockaddr *) &server_address,sizeof(server_address));
      if (connection_status == -1)
      {
       printf("There was an error during connection\n");
      }

      //recieve data from server
        bzero(buffer,1024);
      recv(network_socket,&buffer,sizeof(buffer),0);

      //print out the server's response
      printf("The server sent this data : %s\n",buffer);
      
      bzero(buffer,1024);
      strcpy(buffer,"Hello,this is client\n");
      printf("Client : %s\n",buffer);
      send(network_socket,buffer,strlen(buffer),0);
      
      bzero(buffer,1024);
      recv(network_socket,buffer,sizeof(buffer),0);
      printf("Server : %s\n",buffer);

     //close the socket
     close(network_socket);  
      return 0;

}

