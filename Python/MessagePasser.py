from Controller import Controller
from Message import Message
from Listener import Listener
import pickle
import socket

class MessagePasser:
    controller = None

    def __init__(self, controller):
        self.controller = controller
        listener = Listener(controller)
        listener.start()

    def send(self, message):
        msg_pickle = pickle.dumps(message)
        src = message.src
        dest = message.dest

        host = self.controller.hosts[dest]

        # If the connection doesn't exist, create one
        if (self.controller.conn.has_key(dest) == False):
            client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            client.connect((host.ip, host.port))
            self.controller.conn[dest] = client

        sock = self.controller.conn[dest]
        sock.sendall(msg_pickle)
