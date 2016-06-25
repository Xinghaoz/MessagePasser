class Controller:
    my_name = ""
    my_ip = ""
    my_port = 0
    hosts = {}
    conn = {}

    def __init__(self, name, hosts):
        self.my_name = name
        self.hosts = hosts
        for host in hosts.values():
            if host.name == name:
                self.my_ip = host.ip
                self.my_port = host.port
