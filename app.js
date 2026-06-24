const API = "http://localhost:8080";

function getAuthToken() {
    return localStorage.getItem("auth");
}

function authHeader() {
    return {
        "Authorization": "Basic " + getAuthToken(),
        "Content-Type": "application/json"
    };
}

function requireLogin() {
    if (!getAuthToken()) {
        window.location.href = "login.html";
        return false;
    }
    return true;
}

function logout() {
    localStorage.removeItem("auth");
    window.location.href = "login.html";
}

async function fetchJson(url, options = {}) {
    const response = await fetch(url, options);
    if (response.status === 401 || response.status === 403) {
        logout();
        return null;
    }
    if (!response.ok) {
        const message = await response.text();
        throw new Error(message || "Request failed");
    }
    if (response.status === 204) {
        return null;
    }
    return response.json();
}

function setText(id, value) {
    const element = document.getElementById(id);
    if (element) {
        element.textContent = value;
    }
}

async function loadDashboard() {
    if (!requireLogin()) {
        return;
    }

    const [airports, flights, bookings] = await Promise.all([
        fetchJson(API + "/airports", { headers: authHeader() }),
        fetchJson(API + "/flights?size=100", { headers: authHeader() }),
        fetchJson(API + "/bookings", { headers: authHeader() })
    ]);

    setText("airportCount", airports?.length ?? 0);
    setText("flightCount", flights?.totalElements ?? 0);
    setText("bookingCount", bookings?.length ?? 0);
}

async function loadAirports() {
    if (!requireLogin()) {
        return;
    }

    const airports = await fetchJson(API + "/airports", { headers: authHeader() });
    const table = document.getElementById("airportTable");
    if (!table) {
        return;
    }

    table.innerHTML = airports.map(airport => `
        <tr>
            <td>${airport.airportId}</td>
            <td>${airport.code ?? ""}</td>
            <td>${airport.name ?? ""}</td>
            <td>${airport.city ?? ""}</td>
        </tr>
    `).join("");
}

async function loadFlights() {
    if (!requireLogin()) {
        return;
    }

    const flights = await fetchJson(API + "/flights?size=100", { headers: authHeader() });
    const table = document.getElementById("flightTable");
    if (!table) {
        return;
    }

    table.innerHTML = (flights.content ?? []).map(flight => `
        <tr>
            <td>${flight.flightId}</td>
            <td>${flight.flightNumber ?? ""}</td>
            <td>${flight.origin?.code ?? ""}</td>
            <td>${flight.destination?.code ?? ""}</td>
            <td>${flight.departureTime ?? ""}</td>
            <td>${flight.capacity ?? ""}</td>
            <td>${flight.price ?? ""}</td>
        </tr>
    `).join("");
}

async function loadBookings() {
    if (!requireLogin()) {
        return;
    }

    const bookings = await fetchJson(API + "/bookings", { headers: authHeader() });
    const table = document.getElementById("bookingTable");
    if (!table) {
        return;
    }

    table.innerHTML = bookings.map(booking => `
        <tr>
            <td>${booking.bookingId}</td>
            <td>${booking.passengerName ?? ""}</td>
            <td>${booking.passengerEmail ?? ""}</td>
            <td>${booking.seatNumber ?? ""}</td>
            <td>${booking.bookingStatus ?? ""}</td>
        </tr>
    `).join("");
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("loginForm")?.addEventListener("submit", event => {
        event.preventDefault();
        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value;
        localStorage.setItem("auth", btoa(username + ":" + password));
        window.location.href = "admin-dashboard.html";
    });

    document.getElementById("registerForm")?.addEventListener("submit", async event => {
        event.preventDefault();
        const user = {
            username: document.getElementById("username").value.trim(),
            password: document.getElementById("password").value,
            role: document.getElementById("role").value
        };

        try {
            await fetchJson(API + "/auth/register", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(user)
            });
            alert("User registered");
            window.location.href = "login.html";
        } catch (error) {
            alert(error.message);
        }
    });

    document.getElementById("airportForm")?.addEventListener("submit", async event => {
        event.preventDefault();
        await fetchJson(API + "/airports", {
            method: "POST",
            headers: authHeader(),
            body: JSON.stringify({
                code: document.getElementById("code").value.trim(),
                name: document.getElementById("name").value.trim(),
                city: document.getElementById("city").value.trim()
            })
        });
        event.target.reset();
        await loadAirports();
    });

    document.querySelectorAll("[data-logout]").forEach(button => {
        button.addEventListener("click", logout);
    });

    if (document.body.dataset.page === "dashboard") {
        loadDashboard().catch(error => alert(error.message));
    }
    if (document.body.dataset.page === "airports") {
        loadAirports().catch(error => alert(error.message));
    }
    if (document.body.dataset.page === "flights") {
        loadFlights().catch(error => alert(error.message));
    }
    if (document.body.dataset.page === "bookings") {
        loadBookings().catch(error => alert(error.message));
    }
});
