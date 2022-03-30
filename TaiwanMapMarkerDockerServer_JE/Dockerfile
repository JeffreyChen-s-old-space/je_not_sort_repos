FROM python:3.7-slim
WORKDIR /app
COPY . .
RUN pip install -r requirements.txt
EXPOSE 30005
CMD ["python", "websocket_server.py"]