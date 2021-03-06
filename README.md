# CS460W-Capstone
Capstone project

Installation:
1. Download mongo at https://www.mongodb.com/try/download/community
2. Once downloaded - type in 'mongoimport -d hospital -c staff staff.json' where the directory for staff.json is the directory where the folder "hospital" is stored in the repository. 
For example: C:\users\{user_name}\Documents\GitHub\CS460W-Capstone\hospital
This command will import the pre-installed users.
You have to run this command from the \bin directory of your mongodb installation or add the command to your %PATH% environment variable by entering 'set PATH = %PATH%; C:\Program Files\MongoDB\Server\4.4\bin'
3. Start the executable JAR file
4. To login use one of the following 4 users:

    username: nurse
    password: nurse

    username: doctor
    password: doctor

    username: register
    password: register

    username: biller
    password: biller


To create a new user:
1. Open the mongo terminal
2. Type 'use hospital' verbatim and hit enter
3. Fill out the following template and copy/paste it into the terminal

    db.staff.insert({
        "name": "nurse",
        "id": "4",
        "userName": "nurse",
        "user_role": "NURSE" OR "DOCTOR" OR "REGISTER" OR "BILLING", (must be one of the four in all caps or you will not log in)
        "password": SHA256 encrypted password (can create at https://xorbin.com/tools/sha256-hash-calculator)
    })

4. You can now login using that user
