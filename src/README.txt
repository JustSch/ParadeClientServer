Read Me:

Notes:
If Server And Client Are On Seperate Machines They Both Need the Required folder

Compiling:
1) Open Terminal in Directory of the folders to be used
2) run javac server/*.java or javac client/*.java or javac required/*.java 
	to compile the server, client, or required classes respectively

Running:

1)Run The Server First or the Client may give Exceptions

1) To Start the Server run java server/MainServer portNumber
where port number is the portnumber to  run it on. if no port number
is given it will use the default port of 5000.

2)To Start the Client run java client/MainClient hostName portNumber
from a different terminal window than the server
where the hostname is the host the server is running ie: local host or a bird url
and portnumber to run it on.
If no hostname and portNumber are given it defaults to localhost port 5000

How I Ran It:
The Was Tested with Server running on crane
and Client Running on Pelican by running these commands in terminal:

 java server/MainServer 5000 (in terminal window on crane)
 java client/MainClient crane.cs.qc.cuny.edu 5000  (in terminal window on pelican)

Running on localhost and two terminal windows on one bird also works by running these:
  java server/MainServer (a terminal window on localhost or any of the birds)
  java client/MainClient (a different terminal window also on localhost or the same bird)	

Server may need to be stopped manually with ctrl+c after all clients are finished