import os
import threading

from je_log_system import LogSystem

if __name__ == '__main__':
    lock = threading.Lock()
    log = LogSystem(lock)
    # for circleCI normal run with failed, normal run change os.getcwd() + '/test/setting.jeson to os.getcwd() + '/setting.jeson
    log.load_setting(os.getcwd() + '/test/setting.jeson', None)
    log.log_critical("0")
    log.log_everything_broken("0")
