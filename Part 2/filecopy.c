
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h> // read the number of the last error
#include <fcntl.h> // file descriptor CONSTANT mode values

int main (int argc, char *argv[]) {

    /*
     * File descriptor array.
     * When we create our pipe, we give pipe() this array and it will
     * put the reading[0] and writing[1] file descriptors for the pipe into it.
     */

    int pipeFds[2]; 
    int fileBytesLength;
    char buffer[100];
    char childBuffer[100];
    
    // Check if 3 arguments were supplied.
    if (argc != 3) {
      perror("Filecopy: filecopy.exe [target] [destination]. \n");
      exit(1);
    }
    
    char* srcFile = argv[1];
    char* dstFile = argv[2];

    // Attempt to create a pipe.
    if (pipe(pipeFds) < 0) {
      printf("Something went wrong creating the pipe! %s\n", strerror(errno));
      exit(1);
    }

    // Fork child process
    switch(fork()) {

      // If there was an errorforking a child process
      case -1:
        printf("Error forking child process. %s\n", strerror(errno));
        exit(1);
      
      // If the current executing process is a child process
      // Read the file from upstream parent process and write it to a new file.
      case 0: 
        close(pipeFds[1]);                                                        // Close writing end of pipe upstream.
        ssize_t num_bytes_child = read(pipeFds[0], childBuffer, sizeOf(childBuffer));   // Read file contents from upstream pipe into childBuffer
        close(pipeFds[0]);                                                        // close reading upstream pipe when we're done with it

        int targetDesc = open(dstFile, O_CREAT);                                  // Open a file for writing, create file descriptor.
        write(targetDesc, childBuffer, num_bytes_child);                            // Write contents of buffer to new file descriptor.
        

      // If the current process is the parent process.
      // Read the file and send it down to the child process to write.
      default: 
        close(pipeFds[0]);                                              // close reading end of pipe downstream.
        int fileInDesc = open(srcFile, O_RDONLY);                       // Read file into file descriptor
        ssize_t num_bytes = read(fileInDesc, buffer, sizeof(buffer));   // Get number bytes to read
        write(pipeFds[1], buffer, num_bytes);                           // Write bytes to child process.
        close(pipeFds[1]);                                              // Close writing downstream pipe when we're done with it.

    }

    return 0;
}