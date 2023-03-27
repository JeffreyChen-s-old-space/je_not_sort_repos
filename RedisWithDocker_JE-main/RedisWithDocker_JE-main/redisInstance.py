import redis

redisInstance = redis.ConnectionPool(host='redis_service', port=6379, db=0)
redisInstance = redis.Redis(connection_pool=redisInstance)
print(redisInstance.set('test', '123'))
print(redisInstance.get('test'))
