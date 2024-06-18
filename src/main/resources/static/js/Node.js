const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const port = 3000;

app.use(bodyParser.json());

app.post('/your-backend-endpoint', (req, res) => {
    const { firstName, lastName, phone, email, password, confirmPassword } = req.body;

    // Here you would typically validate and process the data, then save it to a database.
    if (password === confirmPassword) {
        // Simulate a successful registration
        res.json({ success: true });
    } else {
        res.json({ success: false, message: 'Passwords do not match' });
    }
});

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});
