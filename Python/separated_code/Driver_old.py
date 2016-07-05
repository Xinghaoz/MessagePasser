import sys
from YamlReader import YamlReader
import yaml
from Host import Host
from Controller import Controller
from MessagePasser import MessagePasser
from Message import Message

my_name = sys.argv[2]
filename = sys.argv[1]
reader = YamlReader()
hosts = reader.get_hosts(filename)

controller = Controller(my_name,hosts)
mp = MessagePasser(controller)

while True:
    cmd = raw_input(">>")
    cmd_trim = cmd.strip()
    message_array = cmd_trim.split(" ", 1)
    message = Message(my_name, message_array[0], message_array[1])
    mp.send(message)
