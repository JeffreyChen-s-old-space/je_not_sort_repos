import os
import threading
import unittest

from je_log_system import LogSystem


class TestLogSystem(unittest.TestCase):

    def setUp(self) -> None:
        lock = threading.Lock()
        self.log_system = LogSystem(lock)

    def test_set_broadcast_lv(self):
        for num in range(5):
            lock = threading.Lock()
            self.assertEqual(LogSystem(lock).set_boardcast_lv(num), num)

    def test_log(self):
        boardcast = 0
        self.log_system.set_boardcast_lv(boardcast)
        self.assertNotEqual(self.log_system.log_normal(), "Not Run")
        self.assertNotEqual(self.log_system.log_info(), "Not Run")
        self.assertNotEqual(self.log_system.log_debug(), "Not Run")
        self.assertNotEqual(self.log_system.log_warning(), "Not Run")
        self.assertNotEqual(self.log_system.log_error(), "Not Run")
        self.assertNotEqual(self.log_system.log_critical(), "Not Run")
        self.assertNotEqual(self.log_system.log_everything_broken(), "Not Run")

    # for circleCI normal run with failed, normal run change os.getcwd() + '/test/Log.jelog' to os.getcwd() + '/Log.jelog'
    def test_print__log(self):
        with open(os.getcwd() + '/test/Log.jelog', 'r+') as file:
            file.flush()
            print(file.read())


if __name__ == '__main__':
    suite = (unittest.TestLoader().loadTestsFromTestCase(TestLogSystem))
    unittest.TextTestRunner(verbosity=2).run(suite)
