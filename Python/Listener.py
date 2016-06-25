import socket
import pickle
from ListenSession import ListenSession
import threading

class Listener(threading.Thread):
    controller = None

    def __init__(self, controller):
        threading.Thread.__init__(self)
        self.controller = controller

    def run(self):
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.bind((self.controller.my_ip, self.controller.my_port))
        server.listen(25)
        print 'Listeing on the port', self.controller.my_port

        while True:
            conn, addr = server.accept()
            print 'Connected by', addr

            listen = ListenSession(conn)
            listen.start()
        conn.close()
