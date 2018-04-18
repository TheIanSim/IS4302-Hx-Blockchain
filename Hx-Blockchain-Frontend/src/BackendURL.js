const url = '172.25.101.157';//'localhost';
const https = false;
const port = 9191;

export default "http" + (https ? "s" : "") + "://" + url + ":" + port;