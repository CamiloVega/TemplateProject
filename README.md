# TemplateProject
A useful template to get an app started using a local web sever

Uses Android annotations, Jackson annotation and  Picaso

To get started, based on the tutorial from https://stormpath.com/blog/tutorial-build-rest-api-mobile-apps-using-node-js.
All credit for the information below is to them, I'm just putting it in one place. 
Follow this instructions to start a local web server and get this project working

1 - Install the Node.js runtime. You can install the “Current” (v6) version of Node.

2 - Create a folder, navigate to it in the command line or terminal, and run:

  `npm init`

npm will ask you a lot of questions — feel free to leave them as the default!

3- install express by runnini

 ` npm install --save express `
  
4- Create a file named index.js, and type in the following:
```
  var express = require('express')
 
  var app = express()
 
asdfaasdf    res.json({response: "The server is up and running :) "})
  })
 
  app.listen(3000) 
  ```
 
5- Determine your computer ip address (depends on your computer)

6- Run the command:
  
  `node index.js`
  
7- modify the constant ROOT_URL in the file RestManager of the android project. this is so it points to your local server.
  keep in mind that if your ip address is local, your android device should also be in that local network.  

8- build the project and run it 

