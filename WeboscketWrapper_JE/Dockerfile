FROM python:3.7-slim
WORKDIR /app
COPY . .
ADD test/. /test/
RUN pip install -r requirements.txt
EXPOSE 5555
CMD ["python", "./test/server_docker_test.py"]
