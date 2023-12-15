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
