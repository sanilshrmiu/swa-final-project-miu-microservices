<!DOCTYPE html>
<html>
<head>
    <title>${subject}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
        }

        h1 {
            color: #0066cc;
        }

        p {
            color: #444444;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        .header {
            background-color: #f2f2f2;
            padding: 10px;
        }

        .content {
            padding: 20px;
            background-color: #ffffff;
        }

        .footer {
            background-color: #f2f2f2;
            padding: 10px;
            text-align: center;
        }

        .footer p {
            color: #777777;
            font-size: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>${subject}</h1>
    </div>
    <div class="content">
        <p>${message}</p>
    </div>
    <div class="footer">
        <p>Email sent from Email Service:</p>
    </div>
</div>
</body>
</html>
