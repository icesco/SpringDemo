import requests

usersList = requests.get('http://localhost:8080/api/usr/list')

if usersList.status_code == 200:
    print('Success!')
elif usersList.status_code == 404:
    print('Not Found.')

print(usersList.json())
