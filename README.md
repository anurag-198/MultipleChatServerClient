						
					*****************************SINGLE SERVER CLIENT CHAT APPLICATION**************************

	1. The project name is SingleChatApplication . 
	2. It is a netbeans project

	How to run the project -
	1. Open netbeans.
	2. Import the project in netbeans.
	3. Change the hostname in Client.java according to use.
	4. Run the server file ie SingleChatApplication.java
	5. Run the client file ie Client.java
	5. Now you are ready to start the conversation between server and the cleint

	The Wireshark snapshot is attached and its name is connection_single_chat.png (Which shows the initial connection) , single_chat.png(Which shows the data sent by 		server).
	
	Observation : Since there is no encryption applied in this project so we can see the message in Wireshark.
		      In this case it is "hello anurag" which was recieved from server address "172.20.33.231" and
		      sniffed by Wireshark.



				*****************************SINGLE SERVER MULTI CLIENT CHAT APPLICATION**************************


	1. The project name is chatApplication . 
	2. It is a netbeans project

	How to run the project -
	1. Open netbeans.
	2. Import the project in netbeans.
	3. Change the hostname in Client.java according to use.
	4. Run the server file ie ChatApplication.java
	5. Run the client file ie Client.java
	6. Open multiple connextions of Client.java for different users that will communicate with each other.	
	7. Now you are ready to start the conversation between cleints.

	The Wireshark snapshots are attached (having GUI image too ) and the file names are connection_multiple_chat.png(Which shows the initial connection),  multiple_chat.png 		(Which shows the encrypted data sent by server)
	
	Observation : Since there is encryption applied in this project so we can see the encrypted message in Wireshark.
		      In this case we have used many snapshots of messages recieved from server address "172.20.33.231" and
		      sniffed by Wireshark.
		      Clearly the the message is encrypted here.



