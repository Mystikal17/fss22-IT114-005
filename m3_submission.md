<table><tr><td> <em>Assignment: </em> IT114 - Number Guesser</td></tr>
<tr><td> <em>Student: </em> Frank Santos (fss22)</td></tr>
<tr><td> <em>Generated: </em> 10/2/2023 11:20:57 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-number-guesser/grade/fss22" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create the below branch name</li><li>Implement the NumberGuess4 example from the lesson/slides</li><ol><li><a href="https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f">https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f</a><br></li></ol><li>Add/commit the files as-is from the lesson material (this is the base template)</li><li>Pick two (2) of the following options to implement</li><ol><li>Display higher or lower as a hint after a wrong guess</li><li>Implement anti-data tampering of the save file data (reject user direct edits)</li><li>Add a difficulty selector that adjusts the max strikes per level</li><li>Display a cold, warm, hot indicator based on how close to the correct value the guess is (example, 10 numbers away is cold, 5 numbers away is warm, 2 numbers away is hot; adjust these per your preference)</li><li>Add a hint command that can be used once per level and only after 2 strikes have been used that reduces the range around the correct number (i.e., number is 5 and range is initially 1-15, new range could be 3-8 as a hint)</li><li>Implement separate save files based on a "What's your name?" prompt at the start of the game</li></ol><li>Fill in the below deliverables</li><li>Create an m3_submission.md file and fill in the markdown from this tool when you're done</li><li>Git add/commit/push your changes to the HW branch</li><li>Create a pull request to main</li><li>Complete the pull request</li><li>Grab the link to the m3_submission.md from the main branch and submit that direct link to github</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Implementation 1 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-03T02.52.01384531117_347583234289961_6990727391364104436_n.jpg.webp?alt=media&token=6a8485c9-067f-4f56-962f-165d4c205d71"/></td></tr>
<tr><td> <em>Caption:</em> <p>I choose to do the higher or lower hint after a failed guess.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-03T02.52.06386459913_660761405814808_1912965623822529479_n.jpg.webp?alt=media&token=b0b38a69-21fc-402c-a66e-4fd367458ce6"/></td></tr>
<tr><td> <em>Caption:</em> <p>i ran the code and it worked!<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>It was fairly simple to add the higher or lower hint. But all<br>I did was make a if and else if statement where it takes<br>the guess and checks with the number if it is less than it<br>then it prints out the number is higher and for the else if<br>its just the same but with a greater than sign and will display<br>the number is lower. nothing crazy<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Implementation 2 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-03T02.53.57386458257_208891522208571_1345982059997662014_n.jpg.webp?alt=media&token=df195c5a-3e6d-4844-9a1d-9d8bf6e2d0f1"/></td></tr>
<tr><td> <em>Caption:</em> <p>Here I let the user choose difficulty 1-3 and only numbers 1-3 so<br>if they put any other ones then it sets them up with easy<br>by default. Under it I just added the extra parameter that &#39;generateNewNumber&#39; needed<br>for the program to work<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-03T02.54.54384527763_858431755915668_5047525506755795926_n.jpg.webp?alt=media&token=02ab0287-4daf-47ee-bdc0-488b8988fc8e"/></td></tr>
<tr><td> <em>Caption:</em> <p>I choose to do a difficulty option with 3 diffcuilties and here Its<br>me implemeting it so the game can know to what difficulty you are<br>in plus added the range for it to be 1-3.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Ffss22%2F2023-10-03T02.59.42385546804_7175194189182084_6880047564052401039_n.jpg.webp?alt=media&token=73ec5ceb-bf83-44b1-aeb3-4009c51b1a07"/></td></tr>
<tr><td> <em>Caption:</em> <p>Here is the outcome of the code as I ask in the beginning<br>what difficulty the user wants and just to play the game<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>Right away I added a new parameter to generateNewNumber since I want to<br>add a difficulty setting. In that method I added the range of the<br>difficulty to be between 1-3 . After that I went down to the<br>Start method and write a string text that lets the user know to<br>choose a difficulty between 1-3 and set the difficulty to only be between<br>1-3 with a if statement that sets out a string text if you<br>dont pick one of those numbers and follows with a code that sets<br>you to easy mode by default if you dont choose a number between<br>1-3. And lastly, add the last parameter needed for the code to work<br>in the loadState(); which was difficulty.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707834-bf5a5b13-ec36-4597-9741-aa830c195be2.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a link to the related pull request of this hw</td></tr>
<tr><td>Not provided</td></tr>
<tr><td> <em>Sub-Task 2: </em> Discuss anything you learned during this lesson/hw or any struggles you had</td></tr>
<tr><td> <em>Response:</em> <p>I struggled because I had everything in mind for what to do with<br>the code but was lost on where to put it in. Luckily had<br>help but once i knew where to put it, everything went smoothly.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-number-guesser/grade/fss22" target="_blank">Grading</a></td></tr></table>
