<table><tr><td> <em>Assignment: </em> It114 Milestone1</td></tr>
<tr><td> <em>Student: </em> Frank Santos (fss22)</td></tr>
<tr><td> <em>Generated: </em> 10/25/2023 11:44:26 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-milestone1/grade/fss22" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create a new branch called Milestone1</li><li>At the root of your repository create a folder called Project if one doesn't exist yet</li><ol><li>You will be updating this folder with new code as you do milestones</li><li>You won't be creating separate folders for milestones; milestones are just branches</li></ol><li>Create a milestone1.md file inside the Project folder</li><li>Git add/commit/push it to Github (yes it'll be blank for now)</li><li>Create a pull request from Milestone1 to main (don't complete/merge it yet, just have it in open status)</li><li>Copy in the latest Socket sample code from the most recent Socket Part example of the lessons</li><ol><li>Recommended Part 5 (clients should be having names at this point and not ids)</li><li><a href="https://github.com/MattToegel/IT114/tree/Module5/Module5">https://github.com/MattToegel/IT114/tree/Module5/Module5</a>&nbsp;<br></li></ol><li>Fix the package references at the top of each file (these are the only edits you should do at this point)</li><li>Git add/commit the baseline</li><li>Ensure the sample is working and fill in the below deliverables</li><ol><li>Note: The client commands likely are different in part 5 with the /name and /connect options instead of just connect</li></ol><li>Get the markdown content or the file and paste it into the milestone1.md file or replace the file with the downloaded version</li><li>Git add/commit/push all changes</li><li>Complete the pull request merge from step 5</li><li>Locally checkout main</li><li>git pull origin main</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Startup </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot showing your server being started and running</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-20T20.27.22394536840_857396429037318_3446099282715397891_n.png.webp?alt=media&token=a37d7500-2077-4af3-b95e-63492418bd15"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows that server is up and connect to port 3000.  And just<br>waiting for clients to join.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot showing your client being started and running</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-20T20.35.41387532577_218088417821436_1138630186755301170_n.png.webp?alt=media&token=64a24ac1-b914-4665-9d73-f34a952b3458"/></td></tr>
<tr><td> <em>Caption:</em> <p>Client &#39;A&#39; has joined and is waiting for input but is now connected<br>to the server successfully<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain the connection process</td></tr>
<tr><td> <em>Response:</em> <p>The The server is configured to listen for incoming connections on a IP<br>address and port. It creates a socket using a server socket API and<br>binds it to the specified IP and port. This sets up the server<br>to be ready to accept incoming connections. The server enters a waiting state,<br>listening for incoming connection requests from clients. It does this by invoking the<br>&quot;listen&quot; method on the server socket. When a client initiates a connection, the<br>server&#39;s socket accepts the connection request, and a new socket is created specifically<br>for this client to handle the communication. The client knows the server&#39;s IP<br>address and port to connect to. It creates a socket using a client<br>socket API and specifies the server&#39;s IP and port for the connection. The<br>client initiates the connection by calling the &quot;connect&quot; method on the socket. If<br>the server is available and accepting connections, the connection is established, and the<br>client socket is connected to the server&#39;s socket. The server&#39;s socket is created<br>and hooks to the IP and port.<div>The server socket enters a listening state,<br>ready to accept incoming connections. The client creates a socket and initiates the<br>connection to the server. The server&#39;s socket accepts the incoming connection request, creating<br>a new socket dedicated to communication with the client. The server is now<br>in a state where it can send and receive messages from the connected<br>client through the new socket. The server starts listening for incoming messages from<br>the client on this socket.</div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Sending/Receiving </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) showing evidence related to the checklist</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-20T20.39.32393838734_240384129031527_7692976312992599985_n.png.webp?alt=media&token=318980db-4a8b-480e-a060-12e8382cd269"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is showing two clients in 1 room and are connected to the<br>Server. They can send messages and the server is sending the message to<br>all clients in the room. The messages also show their names they choose<br>when they joined.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-20T20.50.37394621708_688423273258549_3620207311483612016_n.png.webp?alt=media&token=ff40237d-d0b7-4ffd-a1c9-76a509d4c201"/></td></tr>
<tr><td> <em>Caption:</em> <p>This had Client A in room 1 and Client B in room 2<br>and they cannot see each others message since they are in different rooms.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the messages are sent, broadcasted (sent to all connected clients), and received</td></tr>
<tr><td> <em>Response:</em> <p>So the basics of it is that the client sends a message that<br>message goes to the serverthread to route to where it needs to go<br>wheather its clients or rooms. The client literally just sends a message and<br>gets routed to where it needs to go and get broadcasted. The serverthread<br>itself holds every client/room connected to where they are. When a message is<br>received from a client, the ServerThread routes the message to the appropriate destination<br>based on the recipient&#39;s information. For broadcasting, the ServerThread sends the message to<br>all clients in a specific room. Rooms will hold groups of clients. Messages<br>sent to a room are distributed by the ServerThread to all clients in<br>that room and then broadcasted. Clients are continuously listening for incoming messages, including<br>those sent to them directly or to rooms they in. When a message<br>is received, the client processes it according to the application&#39;s logic, which may<br>involve displaying the message to the user, handling the data, or taking specific<br>actions based on the content of the message.<div>&nbsp;</div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Disconnecting / Terminating </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707834-bf5a5b13-ec36-4597-9741-aa830c195be2.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) showing evidence related to the checklist</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-20T20.54.51394748975_710500601113397_649090868503629479_n.png.webp?alt=media&token=0daf25cd-78ae-4785-91dd-aa8ecc66adee"/></td></tr>
<tr><td> <em>Caption:</em> <p>Client B used /quit and has been disconnected from the server. The server<br>itself is still up and running and is showing that a client has<br>disconnected and Client A isn&#39;t affected at all.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-20T21.02.02394507577_1543843489763054_8340756735184638907_n.png.webp?alt=media&token=dfada39a-b5cd-46fe-bd86-1be225a24190"/></td></tr>
<tr><td> <em>Caption:</em> <p>I terminated the server and both clients were disconnected right away.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-20T21.04.49393513506_836893617915294_7072754321005861300_n.png.webp?alt=media&token=66a45454-312c-4110-abe8-5c8c111aefef"/></td></tr>
<tr><td> <em>Caption:</em> (missing)</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the various disconnects/terminations are handled</td></tr>
<tr><td> <em>Response:</em> <p>So when the whole disconnects happens it just the makes sure&#39;s that the<br>resources are being cleaned up and closing sockets with the client/server. And if<br>the server is still up it makes sure that other clients dont get<br>affected by it.&nbsp;<div><br><div>-Clients disconnected from a socket POV: It closes the socket from<br>the client it can happen by them randomly disconnecting through network/application issues or<br>just them leaving.The client side would be cleaning up resources that are with<br>the socket, releasing any held connections or memory, and notifying the application logic<br>that the connection is terminated.</div><div><br></div><div>-Client program won&#39;t crash when the server disconnects: When<br>the server disconnects or terminates, the client-side should detect this event through socket<br>errors or notifications. It can then take appropriate action, such as attempting to<br>reconnect, notifying the user, or just leaving the application without crashing.</div><div>Using exception handling<br>and callbacks for socket events can help prevent client crashes and allow for<br>error recovery.</div></div><div><br></div><div>-The server won&#39;t crash from the clients disconnecting: Server side,&nbsp; client disconnections<br>will try to maintain server stability and ensure that the server continues to<br>operate without issues. When a client disconnects, the server will see it and<br>clean up any resources associated with that client, such as closing its socket<br>connection. The server is designed to handle multiple client connections concurrently, so the<br>disconnection of one client should not disrupt the service for other connected clients.<br>This is typically achieved through multithreading or asynchronous programming, allowing the server to<br>continue serving other clients even if one client disconnects.</div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707834-bf5a5b13-ec36-4597-9741-aa830c195be2.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add the pull request for this branch</td></tr>
<tr><td>Not provided</td></tr>
<tr><td> <em>Sub-Task 2: </em> Talk about any issues or learnings during this assignment</td></tr>
<tr><td> <em>Response:</em> <p>No issues really on actually doing the work but still kind of slumped<br>on the whole sockets ideas. I understand the basics of it but it<br>confuses me sometimes ill get it cleared up tho before we continue with<br>the project.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-milestone1/grade/fss22" target="_blank">Grading</a></td></tr></table>

<table><tr><td> <em>Assignment: </em> IT114 Drawing Milestone 2</td></tr>
<tr><td> <em>Student: </em> Frank Santos (fss22)</td></tr>
<tr><td> <em>Generated: </em> 11/15/2023 11:57:45 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-drawing-milestone-2/grade/fss22" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone2 from the proposal document:&nbsp;&nbsp;<a href="https://docs.google.com/document/d/1Fo8FfLph24vNGjnfzxXWv0pHPNk-n5DqxGgmFyU-11I/view">https://docs.google.com/document/d/1Fo8FfLph24vNGjnfzxXWv0pHPNk-n5DqxGgmFyU-11I/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Payload </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Payload Screenshots</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T04.31.33400776642_640976884629736_1419228940177390333_n.png.webp?alt=media&token=4be57bf1-2a69-4895-9152-f2c52dd68cc2"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is my payload class where i put setter and getter for the<br>grid. Its not here but I have my PayloadTypes as a enum which<br>holds CONNECT, DISCONNECT, MESSAGE, START_GAME, GAME_OVER, GRID, ROUND_OVER, READY, GUESS. Plus also have<br>a enum PHASE CLASS which has READY, PREP, GUESS, END. All of them<br>practically as their titles says do what they do. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T04.38.28370213375_1502024820631359_541986582657126765_n.png.webp?alt=media&token=f9ce509a-37ab-4edc-82b5-e36c688ec315"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is where I was using the commands /grid, /ready/ guess. And as<br>you can see its waiting for input which is suppose to be from<br>the drawings but no UI is there for it.<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Board/Grid Implementation (small/simplified 2d array is fine for this deliverable) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the board related code</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T01.34.49387323476_1431536324072201_3842395786056877411_n.png.webp?alt=media&token=db7f1dcd-cace-48c3-847c-ab973be2782a"/></td></tr>
<tr><td> <em>Caption:</em> <p>I made a separate class for my grid. This class just makes how<br>big I want the grid (8x8). Also I have colors for anyone who<br>is going to use usually the drawer. I made a getter/setter for it<br>plus have a method to clear the grid.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T02.37.07387323467_887580216058976_8427333562410793024_n.png.webp?alt=media&token=3ff5a6b7-b392-44f1-b975-45be1cf1a129"/></td></tr>
<tr><td> <em>Caption:</em> <p>This part of the code makes a payload for the client-side and updates<br>it accordingly with it x, y, and color with it. Thats why we<br>have a gridCell method to keep track of them and it gets it<br>from the class. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T02.40.06387331714_880546300040557_1956967885970575301_n.png.webp?alt=media&token=0ed5d316-1f98-4e6a-99a6-55c3079be177"/></td></tr>
<tr><td> <em>Caption:</em> <p>Here is also the clientside of things where the code sends message to<br>the client so that the grid is updated so it can set it,<br>get it, and set it again. For the sync I have a method<br>in Room where it is synced with the room for the server to<br>update the grid. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T04.45.44370213375_1502024820631359_541986582657126765_n.png.webp?alt=media&token=c1165fd0-7c40-4f79-9b49-1eb95773bf65"/></td></tr>
<tr><td> <em>Caption:</em> <p>The debug isnt working for me probably something with the code but this<br>is all i got.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show examples of the board output (2d array)</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T04.44.22399868188_1383796372546378_4066116731560236600_n.png.webp?alt=media&token=24c364a4-04d1-425d-9a07-88d314a9bcfa"/></td></tr>
<tr><td> <em>Caption:</em> <p>So the grid / choosing color option is there and waitng for input<br>but its weird right now but have been trying to fix it better<br>with the professor. Will be changing projects to RPS.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Explain at a high level the board logic implementation</td></tr>
<tr><td> <em>Response:</em> <p>In the gameroom i got the grid object in it. I have a<br>method so it updates it. Like I said before we have a setter<br>and getter for it. But the whole purpose of it is so that<br>the grid will be used for drawing/displaying the game board where actions like<br>drawing by a player or guessing the drawn word might take place. The<br>update grid method also is used to sync the board&#39;s state across all<br>clients in the room. The board logic has cells in it so they<br>show where the player is with a color but isnt yet implemented due<br>to the terminal.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Picking Words </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for how a word is chosen</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T01.00.54387338099_288854440158322_7076919288996830492_n.png.webp?alt=media&token=0665cfa9-d9b6-460d-8947-7a0117f1f6ac"/></td></tr>
<tr><td> <em>Caption:</em> <p>I made a separate class for my wordList.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T01.05.02387340785_299754873025503_5924011736514771933_n.png.webp?alt=media&token=59735041-c222-480d-9f86-55a86ff7271c"/></td></tr>
<tr><td> <em>Caption:</em> <p>I made a method so the word gets sent to this and gets<br>transformed to underscores<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T01.07.09393813240_381038557598402_2227375498410488448_n.png.webp?alt=media&token=cfef0026-304a-4883-8522-0ad53bda6b0c"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is where the blankword method gets sent to once its all underscores.<br>It is sent to the guessers which is the people not drawing.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-16T01.08.39387338101_226827593633064_335759743266913477_n.png.webp?alt=media&token=f05c4c5a-9c52-4c80-9469-41435491ffd0"/></td></tr>
<tr><td> <em>Caption:</em> <p>Here is the guessing logic. whatever the clients are guessing have to equal<br>the actualword but doesn&#39;t matter if its lower or upper case and the<br>server will dm the client when the word is correct.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Explain the logic related to the word choosing and syncing</td></tr>
<tr><td> <em>Response:</em> <p>So all the room class does is grab from the wordList class thats<br>whole thing. The room class has a wordList attribute initialized by calling WordList.getWordList()<br>in its constructor which of course grabs the words in the wordList class.<br>I have a method that calls/fetches the words from the class which gets<br>put in a list and then when everything gets moved to that list<br>in the room class which then gets shuffled to get them at random.<br>When you start the game the drawer is chosen and gets sent the<br>word from the collection picked randomly and continues after every round whoever the<br>drawer is.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Timer and Round-end </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for your timer and round-end logic</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-15T23.40.55400066554_1488674795256441_5229688521914394513_n.png.webp?alt=media&token=1dc0e92a-1678-4b11-8019-672fe36e5f75"/></td></tr>
<tr><td> <em>Caption:</em> <p>This code shows a timer for 2 phases (GUESS, PREP). I commented on<br>the code so it explains what it does in the SS. But quickly<br>makes 45 second timer for the phases and prep is for the drawer<br>to draw and the guess is for players who will guess after the<br>prep phase is done. I used it a lot of it. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-11-15T23.44.42399773922_1496528334224945_7133748962260443314_n.png.webp?alt=media&token=d35966b4-b241-4f50-9e36-2463373c6661"/></td></tr>
<tr><td> <em>Caption:</em> <p>Here the Timer is used so when all players have correctly guessed the<br>word then the round ends and continues to the next round. The timer<br>doesnt cancel or stop unless the time runs out or every guesses correct.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the timers work in your project</td></tr>
<tr><td> <em>Response:</em> <p>The timers are used to manage the different phases of the game. It<br>practically orchestrates the flow of the game. Transitions, player actions, rounds all for<br>a set amount of time. The game progresses through different phases (Phase.PREP, Phase.GUESS,<br>Phase.END) managed by the timers and specific logic for each phase.<div>handleEndOfRound() is called<br>to handle transitions between phases and perform phase-specific actions such as informing players<br>about the end of a round, handling guess correctness, and selecting the drawer<br>for the next round. But I used the java.util.Timer and TimerTask to use<br>the timer and depending on what method I used it in it varies<br>from a final time or the usual round time which is 45 seconds.<br>Even delayed the time between round to give the clients time like 10<br>seconds.</div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 5: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Include the pull request for Milestone2 to main</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/Mystikal17/fss22-IT114-005/pull/8">https://github.com/Mystikal17/fss22-IT114-005/pull/8</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-drawing-milestone-2/grade/fss22" target="_blank">Grading</a></td></tr></table>

<table><tr><td> <em>Assignment: </em> IT114 RPS Milestone3</td></tr>
<tr><td> <em>Student: </em> Frank Santos (fss22)</td></tr>
<tr><td> <em>Generated: </em> 12/14/2023 7:33:55 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-rps-milestone3/grade/fss22" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone3 from the proposal document:&nbsp;&nbsp;<a href="https://docs.google.com/document/d/11SRMo7JkLAMM-PuuiGwl_Z-QXP3pyQ7xN3lRxwmcwCc/view">https://docs.google.com/document/d/11SRMo7JkLAMM-PuuiGwl_Z-QXP3pyQ7xN3lRxwmcwCc/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Connection Screens </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshots showing the screens with the following data</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-12-14T23.53.08Screenshot%202023-12-14%20at%206.52.11%E2%80%AFPM.png.webp?alt=media&token=4319147b-baae-407d-be44-cba1ce34946c"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is the Host and Port window panel to connect to the server<br>for the Clients<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-12-14T23.53.14Screenshot%202023-12-14%20at%206.52.31%E2%80%AFPM.png.webp?alt=media&token=f4ded803-8f42-4817-a2e1-7b668266a642"/></td></tr>
<tr><td> <em>Caption:</em> <p>Here is where the client have to type in the username they will<br>use for the rooms<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the code for each step of the process</td></tr>
<tr><td> <em>Response:</em> <div>It begins with the ClientUI class extending JFrame to create the main frame<br>for the UI. It implements IClientEvents and ICardControls classes interfaces, which define methods<br>to handle various client events and manage UI cards/panels. It then Initializes various<br>panels like ConnectionPanel, UserInputPanel, RoomsPanel, ChatPanel, ChatGamePanel, and sets up the main card<br>layout. Sets up the window's minimum size, menu bar, and other window-related settings.<br>Creates a Hashtable to maintain a mapping between client IDs and names. Implements<br>a confirmation dialog when attempting to close the window. next(), previous(), and show(String<br>cardName) methods control the card layout by navigating between different panels. addPanel(String cardName,<br>JPanel panel) adds panels to the card layout. It handles the connection method<br>which gets the username, host, and port information from the panels and initiates<br>a connection using Client.INSTANCE. The onReceiveClientId(long id) method receives and handles the client<br>ID from the server.</div><div><br></div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Game view </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshots showing the related UI</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-12-15T00.15.04Screenshot%202023-12-14%20at%207.14.20%E2%80%AFPM.png.webp?alt=media&token=f5bdd015-2280-478e-86e2-eeaab3e04d6a"/></td></tr>
<tr><td> <em>Caption:</em> <p>This shows the list of users on the right side panel. Also shows<br>that everyone is waiting for the rest to pick a choice; The text<br>area/ game event history is all in the middle panel. The RPS selection<br>is the panel on the left. The countdown timer is as well in<br>the middle plus I showed what it says when time is up and<br>no one has chosen a choice.<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Game Logic </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code snippets for part of the game flow</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-12-15T00.30.40Screenshot%202023-12-14%20at%207.14.45%E2%80%AFPM.png.webp?alt=media&token=2c632e1b-5149-419c-94b5-a613d48bfa84"/></td></tr>
<tr><td> <em>Caption:</em> <p>Here we can see the timer going down as the players have to<br>make a choice. Also shows that the game/round started since everyone was ready<br>up then they had the chance to pick a choice and had 30<br>seconds to choose. Since all of them didn&#39;t choose a choice then the<br>game automatically canceled and has told all of the players to ready up<br>again since no one has chosen anything.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the code flow for each of the steps of the game flow mentioned previously</td></tr>
<tr><td> <em>Response:</em> <div>This Game Room Code holds all the logic for the flow of the<br>game:</div><div><br></div><div>Initializes the game's phase (Phase.READY) and sets up necessary data structures.<br></div><div><br></div><div>Player Management:</div><div>Adds players<br>to the game room when they connect and sets their readiness status.</div><div>Manages player<br>disconnections and removes them from the game when disconnected.</div><div>Syncs game state and readiness<br>status between players.</div><div><br></div><div>Game Start and Ready Check:</div><div>Initiates a ready check for players before<br>starting the game.</div><div>If enough players are ready within the time limit, it starts<br>the game by moving to the Phase.SELECTION.</div><div><br></div><div>Selection Phase:</div><div>Begins the selection phase with a<br>time limit (e.g., 10 seconds) for players to make their choices.</div><div>Uses a timer<br>to manage the selection time.</div><div>Once the time is up, it triggers the end<br>of the selection phase.</div><div><br></div><div>Choice Handling and Result Calculation:</div><div>Tracks each player's choice (Rock, Paper,<br>Scissors) using a map.</div><div>Once all choices are received, it calculates and compares the<br>choices.</div><div>Determines the winner based on the Rock-Paper-Scissors game logic and sends the result<br>to all players.</div><div>Handles ties and updates player statuses accordingly (e.g., marking a losing<br>player as a spectator).</div><div>Checks if the game continues or ends based on the<br>number of remaining players this is considered as the round system.</div><div><br></div><div>End Game:</div><div>Ends the<br>game if there's only one player remaining or no players left.</div><div>Resets the session<br>or performs any necessary actions.</div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Pull request from milestone3 to main</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/Mystikal17/fss22-IT114-005/pull/9">https://github.com/Mystikal17/fss22-IT114-005/pull/9</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-rps-milestone3/grade/fss22" target="_blank">Grading</a></td></tr></table>

<table><tr><td> <em>Assignment: </em> IT114 RPS Milestone4</td></tr>
<tr><td> <em>Student: </em> Frank Santos (fss22)</td></tr>
<tr><td> <em>Generated: </em> 12/14/2023 10:10:44 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-rps-milestone4/grade/fss22" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone4 from the proposal document:&nbsp;&nbsp;<a href="https://docs.google.com/document/d/11SRMo7JkLAMM-PuuiGwl_Z-QXP3pyQ7xN3lRxwmcwCc/view">https://docs.google.com/document/d/11SRMo7JkLAMM-PuuiGwl_Z-QXP3pyQ7xN3lRxwmcwCc/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Client can mark themselves “away” to be skipped in the turn flow but still be in the game </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707795-a9c94a71-7871-4572-bfae-ad636f8f8474.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) of the visual representation of someone "away"</td></tr>
<tr><td><table><tr><td>Missing Image</td></tr>
<tr><td> <em>Caption:</em> (missing)</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the code logic</td></tr>
<tr><td> <em>Response:</em> <p>(missing)</p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Client can join as spectator </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) of what a spectator can see</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-12-15T00.47.00Screenshot%202023-12-14%20at%207.46.14%E2%80%AFPM.png.webp?alt=media&token=a070d285-0b4b-4dd0-b14b-9b731b05dfa4"/></td></tr>
<tr><td> <em>Caption:</em> <p>The code asks the client themselves if they will be playing or just<br>spectating.If they spectate then they get moved to the spectator list that I<br>have set for them and they cant do anything besdies watch the game.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the code handles spectators</td></tr>
<tr><td> <em>Response:</em> <div>addClient method:</div><div>This method is called when a new client connects to the server<br>and attempts to join a game room.</div><div>It starts by asking the client if<br>they want to join as a spectator.</div><div><br></div><div>If the client chooses to join as<br>a spectator (joinAsSpectator is true):</div><div>It adds the client to the spectators list.</div><div>Calls super.addClient(client)<br>to handle general client addition logic.</div><div>Synchronizes the game state with the client.</div><div>Logs the<br>total number of clients in the room.</div><div><br></div><div>If the client chooses not to join<br>as a spectator (joinAsSpectator is false):</div><div>It attempts to add the client as a<br>player to the players map using computeIfAbsent.</div><div><br></div><div>If the player is successfully added:</div><div>Creates a<br>ServerPlayer object for the client.</div><div>Calls super.addClient(client) to handle general client addition logic.</div><div>Synchronizes the<br>game state with the client.</div><div>Logs the total number of clients in the room.</div><div><br></div><div>askClientToJoinAsSpectator<br>method:</div><div>This method sends a message to the client, asking if they want to<br>join as a spectator.</div><div>It expects a response from the client (presumably 'Y' or<br>'N') and checks if it's 'Y' (case insensitive).</div><div>Returns true if the client response<br>is 'Y', indicating they want to join as a spectator; otherwise, it returns<br>false.</div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Implement extra options beyond Rock Paper and Scissors </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707795-a9c94a71-7871-4572-bfae-ad636f8f8474.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) showing the extra options/choices</td></tr>
<tr><td><table><tr><td>Missing Image</td></tr>
<tr><td> <em>Caption:</em> (missing)</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain what you added</td></tr>
<tr><td> <em>Response:</em> <p>(missing)</p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Implement a cooldown on an option (i.e., same option can’t be picked twice in a row by the same player) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707795-a9c94a71-7871-4572-bfae-ad636f8f8474.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) showing the cooldown active where option can't be picked</td></tr>
<tr><td><table><tr><td>Missing Image</td></tr>
<tr><td> <em>Caption:</em> (missing)</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the code logic</td></tr>
<tr><td> <em>Response:</em> <p>(missing)</p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-rps-milestone4/grade/fss22" target="_blank">Grading</a></td></tr></table>
