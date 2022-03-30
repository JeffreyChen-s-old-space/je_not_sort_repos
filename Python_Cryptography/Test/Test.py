import JECryptography

Hash = JECryptography.Hash()

if __name__ == "__main__":
    print(Hash.hash_md5("test"))
    print(Hash.hash_sha1("test"))
    print(Hash.hash_sha224("test"))
    print(Hash.hash_sha256("test"))
    print(Hash.hash_sha384("test"))
    print(Hash.hash_sha512("test"))
