import sys
import yaml
import socket
import pickle
import threading

class Controller:
    def __init__(self, name, hosts):
        self.my_name = name
        self.hosts = hosts
        self.conn = {}
        for host in hosts.values():
            if host.name == name:
                self.my_ip = host.ip
                self.my_port = host.port

class Host:
    def __init__(self):
        self.name = ""
        self.ip = ""
        self.port = 0

class Message:
    def __init__(self, src, dest, message):
        self.src = src
        self.dest = dest
        self.message = message

class Listener(threading.Thread):
    def __init__(self, controller):
        threading.Thread.__init__(self)
        self.controller = controller

    def run(self):
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.bind((self.controller.my_ip, self.controller.my_port))
        server.listen(25)
        print 'Listening on the port', self.controller.my_port

        while True:
            conn, addr = server.accept()
            print 'Connected by', addr

            listen = ListenSession(conn)
            listen.start()

class ListenSession(threading.Thread):
    def __init__(self, conn):
        threading.Thread.__init__(self)
        self.conn = conn

    def run(self):
        while True:
            data_recv = self.conn.recv(1024)
            data = pickle.loads(data_recv)
            print 'Received from', data.src, ':', data.message

class MessagePasser:
    def __init__(self, controller):
        self.controller = controller
        listener = Listener(controller)
        listener.start()

    def send(self, message):
        msg_pickle = pickle.dumps(message)
        src = message.src
        dest = message.dest

        host = self.controller.hosts[dest]

        if (self.controller.conn.has_key(dest) == False):
            client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            client.connect((host.ip, host.port))
            self.controller.conn[dest] = client

        sock = self.controller.conn[dest]
        sock.sendall(msg_pickle)

def get_hosts(filename):
    stream = file(filename, 'r')
    hosts = {}
    for conf in yaml.load_all(stream):
        for key_value in conf['configuration']:
            host = Host()
            for key in key_value:
                setattr(host, key, key_value[key])
            hosts[host.name] = host
    return hosts

if __name__ == "__main__":
    filename = sys.argv[1]
    my_name = sys.argv[2]
    hosts = get_hosts(filename)

    controller = Controller(my_name, hosts)
    mp = MessagePasser(controller)

    while True:
        cmd = raw_input(">>")
        cmd_trim = cmd.strip()
        message_array = cmd_trim.split(" ", 1)
        if len(message_array) < 2:
            print "Invalid input"
            continue
        message = Message(my_name, message_array[0], message_array[1:])
        mp.send(message)
