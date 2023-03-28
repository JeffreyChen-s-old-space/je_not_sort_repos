def verify_status(status: str):
    if status == "status_verified":
        return 200
    return 404
