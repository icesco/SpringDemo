import requests
import sys
import json

if (len(sys.argv) < 5):
    print("Missing arguments")
else:
    identifier = sys.argv[1]
    name = sys.argv[2]
    surname = sys.argv[3]
    password = sys.argv[4]
    
    body = {'id' : identifier, 'name' : name, 'surname' : surname, 'password': password}

    usersList = requests.put('http://localhost:8080/api/usr/update',
                                            json= body)


    if usersList.status_code == 200:
        print('Success!')
    elif usersList.status_code == 404:
        print('Not Found.')


