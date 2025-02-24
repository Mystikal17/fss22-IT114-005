# fss22-IT114-005

Multiplayer Rock-Paper-Scissors Game 🎮
📌 Description
This is a multiplayer Rock-Paper-Scissors game built using Java sockets for real-time client-server communication. The game allows multiple players to connect, play rounds, and receive results in a server-managed environment.

💡 Why This Project?
This project was developed as part of a networking and Java programming course to explore socket programming, recursion, and real-time communication between clients and a server. The goal was to build an interactive multiplayer game while learning about concurrent client handling and efficient data transmission in a networked environment.

🛠 Tech Stack
Language: Java
Networking: Java Sockets (TCP/IP)
Concurrency: Multi-threaded server
File I/O: Logging game sessions
Version Control: Git/GitHub

📁 Project Structure
bash
Copy
Edit
📦 RPS-Multiplayer
 ┣ 📂 src
 ┃ ┣ 📜 Client.java    # Handles user input and connects to the server
 ┃ ┣ 📜 Server.java    # Manages game logic, client connections
 ┃ ┣ 📜 GameLogic.java # Contains game rules and winner determination
 ┃ ┣ 📜 Logger.java    # Logs game sessions to a file
 ┗ 📜 README.md        # Project documentation
 
🚀 Features
✔️ Multiplayer functionality – Supports multiple players connecting to the server
✔️ Real-time interaction – Players send their choices, and results are processed instantly
✔️ Recursive game loop – Allows continuous play without restarting the server
✔️ File logging – Game history is saved for analysis
✔️ Error handling – Robust exception handling for unexpected inputs

🎮 How to Play
Start the server – One instance of Server.java should be running.
Run the client – Each player runs Client.java and connects to the server.
Make your move – Players input rock (R), paper (P), or scissors (S).
Receive results – The server determines the winner and sends back the result.
Play again – The game continues until a player chooses to exit.

🔧 Future Improvements
Add a GUI for better user experience
Implement a database to store player stats and history
Support more game modes (e.g., best of 3 rounds, tournament mode)
Isn't completely done but just need tweaks to the GUI.

📝 License
This project is open-source and available under the MIT License.
