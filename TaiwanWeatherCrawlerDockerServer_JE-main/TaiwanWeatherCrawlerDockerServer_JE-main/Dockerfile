FROM python:3.7-slim
WORKDIR /app
COPY . .
RUN pip install -r requirements.txt
EXPOSE 30003
CMD ["python", "websocket_server.py"]