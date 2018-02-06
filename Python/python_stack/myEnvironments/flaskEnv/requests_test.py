# source flaskEnv/bin/activate

import requests
import md5
import time
import math
import json
import sys

print sys.argv

urlBase = "http://gateway.marvel.com/v1/public/characters";
ts = int(time.time())
publicKey = "2e99e7d69ee86008b2a276cb265d0cd3";
privateKey = sys.argv[1]

param_set = {"ts": ts,
			"apikey": publicKey,
			"hash": md5.new(str(ts)+privateKey+publicKey).hexdigest(),
			"limit":5
			}

r = requests.get(urlBase, params=param_set)

print r.url
#print r.json()
json_text = r.json()
#print json_text
#json_text = r.text
char_data = json_text["data"]["results"]
#print char_data
for i in range(len(char_data)):
#There is state is field of database
    print char_data[i]['name']