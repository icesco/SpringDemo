import requests
import sys

if (len(sys.argv) < 2):
    print("Missing identifier argument")
else:
    identifier = sys.argv[1]
    surname = sys.argv[2]
    query = [("name", identifier), ("surname", surname)]

    usersList = requests.post('http://localhost:8080/dummy', params = query)


    if usersList.status_code == 200:
        print('Success!')
    elif usersList.status_code == 404:
        print('Not Found.')



