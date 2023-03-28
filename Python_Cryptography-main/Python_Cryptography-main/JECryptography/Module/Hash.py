import hashlib


class Hash:

    @staticmethod
    def hash_md5(message):
        algorithm = hashlib.md5()
        return update_hash(algorithm, message)

    @staticmethod
    def hash_sha1(message):
        algorithm = hashlib.sha1()
        return update_hash(algorithm, message)

    @staticmethod
    def hash_sha224(message):
        algorithm = hashlib.sha224()
        return update_hash(algorithm, message)

    @staticmethod
    def hash_sha256(message):
        algorithm = hashlib.sha256()
        return update_hash(algorithm, message)

    @staticmethod
    def hash_sha384(message):
        algorithm = hashlib.sha384()
        return update_hash(algorithm, message)

    @staticmethod
    def hash_sha512(message):
        algorithm = hashlib.sha512()
        return update_hash(algorithm, message)


def update_hash(algorithm, message):
    algorithm.update(str.encode(message))
    hashString = algorithm.hexdigest()
    return hashString
