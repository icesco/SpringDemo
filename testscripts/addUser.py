import requests
import sys

if (len(sys.argv) < 3):
    print("Missing identifier argument")
else:
    identifier = sys.argv[1]
    surname = sys.argv[2]
    password = sys.argv[3]
    query = [("name", identifier), ("surname", surname), ("password", password)]

    usersList = requests.post('http://localhost:8080/api/usr/dummy', params = query)


    if usersList.status_code == 200:
        print('Success!')

        print(usersList.json())
    elif usersList.status_code == 404:
        print('Not Found.')



