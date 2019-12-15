Read Me:

Notes:
If Server And Client Are On Seperate Machines They Both Need a copy of the Required folder

Compiling:
1) Open Terminal in the directory that contains the folders client, server, required 
which are extracted from the .zip file
2) compile all the java classes by running
javac server/*.java 
javac client/*.java  
javac required/*.java 


Note:
1)Run The Server First or the Client may give Exceptions
 
Running:

1) To Start the Server run 

java server/MainServer portNumber

where portNuumber is the port number to  run it on. if no port number
is given it will use the default port of 5000.

2)To Start the Client run 

java client/MainClient hostName portNumber

from a different terminal window than the server
where the hostname is the host the server is running ie: localhost or a bird url
and portNumber is the port number to run it on.
If no hostname and portNumber are given it defaults to localhost port 5000
If only one value is given it may still use default values for both


How I Ran It:
They were placed in a folder named cs344 so on both terminals the first command run was
cd cs344

I compiled with java 1.7 on crane

This Was Tested with Server running on Crane
and Client running on Pelican by running these commands in terminal:


 java server/MainServer 5000 (in terminal window on crane)
 java client/MainClient crane.cs.qc.cuny.edu 5000  (in terminal window on pelican)

Running on localhost and two terminal windows on one bird also works by running these:
  java server/MainServer (a terminal window on localhost or any of the birds)
  java client/MainClient (a different terminal window also on localhost or the same bird)	

Server will need to be stopped manually with ctrl+c after all clients are finished