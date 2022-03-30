from je_database import sqlite_core

marker_sql = sqlite_core(r"Marker.sqlite", table_name="Marker", check_same_thread=False)


def create_database():
    marker_sql.create_table("CREATE TABLE IF NOT EXISTS Marker("
                            "Id INTEGER PRIMARY KEY, "
                            "Title VARCHAR (50),"
                            "Snippet VARCHAR (200),"
                            "Px FLOAT (15),"
                            "Py FLOAT (15))")
