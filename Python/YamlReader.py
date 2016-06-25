import yaml
from Host import Host

class YamlReader():
    def get_hosts(self, filename):
        stream = file(filename, 'r')
        hosts = {}
        for conf in yaml.load_all(stream):
            for key_value in conf['configuration']:
                host = Host()
                for key in key_value:
                    setattr(host, key, key_value[key])
                #print host.name, host.ip, host.port
                hosts[host.name] = host
        return hosts
