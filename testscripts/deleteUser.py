import requests
import sys

if (len(sys.argv) < 2):
    print("Missing identifier argument")
else:
    identifier = sys.argv[1]
    query = [("identifier", identifier)]

    usersList = requests.delete('http://localhost:8080/deleteUser', params = query)


    if usersList.status_code == 200:
        print('Success!')
    elif usersList.status_code == 404:
        print('Not Found.')


