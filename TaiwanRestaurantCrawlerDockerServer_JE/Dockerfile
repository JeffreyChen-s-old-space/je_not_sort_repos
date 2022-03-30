FROM python:3.7-slim
WORKDIR /app
COPY . .
RUN pip install -r requirements.txt
EXPOSE 30004
CMD ["python", "websocket_server.py"]