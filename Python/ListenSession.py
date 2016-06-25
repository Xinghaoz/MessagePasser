import threading
import pickle

class ListenSession(threading.Thread):
    conn = None

    def __init__(self, conn):
        threading.Thread.__init__(self)
        self.conn = conn

    def run(self):
        while True:
            data_recv = self.conn.recv(1024)
            data = pickle.loads(data_recv)
            print 'Received from', data.src, ':', data.message
