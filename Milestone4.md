<table><tr><td> <em>Assignment: </em> IT114 RPS Milestone4</td></tr>
<tr><td> <em>Student: </em> Frank Santos (fss22)</td></tr>
<tr><td> <em>Generated: </em> 12/14/2023 7:55:40 PM</td></tr>
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
