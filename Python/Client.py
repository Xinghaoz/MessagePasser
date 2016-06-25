import socket
import json
import pickle
from Message import Message

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(("127.0.0.1", 22377))

while True:
    cmd = raw_input(">>")
    msg_pickle = pickle.dumps(cmd)
    msg = Message("alice", "frank", cmd)
    msg_pickle = pickle.dumps(msg)
    client.sendall(msg_pickle)
    #msg_json = json.dumps(msg)
    #client.sendall(msg_json)

client.close()
