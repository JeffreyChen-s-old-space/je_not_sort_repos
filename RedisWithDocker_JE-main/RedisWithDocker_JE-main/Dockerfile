FROM python:3.7-slim
WORKDIR /app
COPY . .
RUN pip install -r requirements.txt
EXPOSE 6379
CMD ["python", "redisInstance.py"]