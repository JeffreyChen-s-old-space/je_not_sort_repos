import unittest

from je_database.core.sqlite_core import sqlite_core


class TestDatabase(unittest.TestCase):

    def setUp(self) -> None:
        self.SQL = sqlite_core(r'TestAccount.sqlite', table_name='Account', check_same_thread=True)
        self.SQL.create_table(
            'CREATE TABLE IF NOT EXISTS Account(id VARCHAR(20),email VARCHAR(50),password VARCHAR(15))')

    def tearDown(self) -> None:
        self.SQL.close()

    def test_insert(self):
        self.SQL.insert_into_ignore(1, 'test1@gmail.com', 'test_password')
        self.SQL.insert_into_ignore(2, 'test2@gmail.com', 'test_password')
        self.SQL.insert_into_ignore(3, 'test3@gmail.com', 'test_password')

    def test_delete(self):
        self.SQL.delete('email', 'test1@gmail.com')

    def test_select(self):
        self.SQL.select_form()
        self.SQL.select_distinct()

    def test_update(self):
        self.SQL.update('email', 'email', 'new_test@gmail.com', 'test_password')

    def test_select_account(self):
        self.SQL.select_prefix = '*'
        self.SQL.table_name = 'Account'
        self.SQL.select_account('email', 'password', 'new_test@gmail.com', 'test_password')


if __name__ == '__main__':
    suite = (unittest.TestLoader().loadTestsFromTestCase(TestDatabase))
    unittest.TextTestRunner(verbosity=2).run(suite)
