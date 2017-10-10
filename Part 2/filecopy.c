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
    
    // Check if 3 arguments were supplied.
    if (argc != 3) {
      perror("Filecopy: filecopy.exe [target] [destination]. \n");
      exit(1);
    }
    
    char*[] srcFile = argv[1];
    char*[] dstFile = argv[2];

    // Attempt to create a pipe.
    if (pipe(pipeFds) < 0) {
      perror("Something went wrong creating the pipe! %s\n", strerror(errno));
      exit(1);
    }

    // Fork child process
    switch(fork()) {

      // If there was an errorforking a child process
      case -1:
        perror("Error forking child process. %s\n", strerror(errno));
        exit(1);
      
      // If the current executing process is a child process
      case 0: 
        

      // If the current process is the parent process.
      default: 

        int filedesc = open(srcFile, O_RDONLY);

    }

    

    // target = open("copy.txt", O_CREAT |O_WRONLY, 00777);

    // // ======================================
    // // ========== CHILD PROCESS =============
    // // ======================================


    // ssize_t count = read( fd[0], buffer, sizeof(buffer)-1 );
    printf("%d", argc);
    return 0;
}