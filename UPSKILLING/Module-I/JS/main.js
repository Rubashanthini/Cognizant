console.log("Welcome to the Community Portal");

/* =========================
   TASK 2
========================= */

const eventName = "Community Workshop";
const eventDate = "15 June 2026";
let seats = 50;

console.log(`
Event: ${eventName}
Date: ${eventDate}
Available Seats: ${seats}
`);

function registerSeat() {

    if (seats > 0) {

        seats--;

        document.getElementById("seatDisplay").innerHTML =
            "Available Seats: " + seats;
    }
}

/* =========================
   TASK 3
========================= */

const events = [

    {
        name: "Workshop",
        seats: 20,
        upcoming: true
    },

    {
        name: "Sports",
        seats: 0,
        upcoming: true
    },

    {
        name: "Music Night",
        seats: 15,
        upcoming: false
    }
];

function displayEvents() {

    const container =
        document.getElementById("eventList");

    if (!container) return;

    container.innerHTML = "";

    events.forEach(event => {

        if (event.upcoming && event.seats > 0) {

            container.innerHTML += `
                <p>
                    ${event.name}
                    - Seats Available:
                    ${event.seats}
                </p>
            `;
        }
    });
}

function safeRegistration() {

    try {

        if (seats <= 0) {

            throw new Error(
                "No seats available"
            );
        }

        registerSeat();

        alert(
            "Registration Successful"
        );

    } catch (error) {

        alert(
            "Error: " +
            error.message
        );
    }
}

/* =========================
   TASK 4
========================= */

function addEvent(eventArray, event) {

    eventArray.push(event);
}

function registerUser(userName, eventName) {

    return `${userName} registered for ${eventName}`;
}

function filterEventsByCategory(events, callback) {

    return events.filter(callback);
}

function registrationCounter() {

    let totalRegistrations = 0;

    return function () {

        totalRegistrations++;

        return totalRegistrations;
    };
}

const musicRegistrationCounter =
    registrationCounter();

function demoFunctions() {

    const registrationMessage =
        registerUser(
            "Ruba Shanthini",
            "Music Night"
        );

    const musicCount =
        musicRegistrationCounter();

    document.getElementById(
        "functionOutput"
    ).innerHTML = `
        <p>${registrationMessage}</p>
        <p>Total Music Registrations:
        ${musicCount}</p>
    `;
}

/* =========================
   TASK 5
========================= */

class Event {

    constructor(
        name,
        category,
        seats
    ) {

        this.name = name;
        this.category = category;
        this.seats = seats;
    }
}

Event.prototype.checkAvailability =
function () {

    return this.seats > 0
        ? "Seats Available"
        : "Full";
};

const event1 =
    new Event(
        "Music Night",
        "Music",
        20
    );

Object.entries(event1).forEach(
    ([key, value]) => {

        console.log(
            key + ": " + value
        );
    }
);

/* =========================
   TASK 6
========================= */

let communityEvents = [

    new Event(
        "Music Night",
        "Music",
        20
    ),

    new Event(
        "Workshop on Baking",
        "Workshop",
        15
    ),

    new Event(
        "Sports Meet",
        "Sports",
        25
    )
];

const onlyMusicEvents =
    communityEvents.filter(
        event =>
            event.category === "Music"
    );

console.log(onlyMusicEvents);

const displayCards =
    communityEvents.map(
        event =>
            `Event: ${event.name}`
    );

document.getElementById(
    "arrayOutput"
).innerHTML =
    displayCards.join("<br>");

/* =========================
   TASK 7
========================= */

const eventContainer =
    document.querySelector(
        "#eventContainer"
    );

function renderEvents(eventsToRender = communityEvents) {

    if (!eventContainer) return;

    eventContainer.innerHTML = "";

    eventsToRender.forEach(event => {

        const card =
            document.createElement(
                "div"
            );

        card.className =
            "eventCard";

        card.innerHTML = `
            <h3>${event.name}</h3>
            <p>Category:
            ${event.category}</p>
            <p>Seats:
            ${event.seats}</p>

            <button
            onclick="registerEvent('${event.name}')">
                Register
            </button>
        `;

        eventContainer.appendChild(
            card
        );
    });
}

function registerEvent(eventName) {

    alert(
        "Registered for " +
        eventName
    );
}

/* =========================
   TASK 8
========================= */

const categoryFilter =
    document.getElementById(
        "categoryFilter"
    );

if (categoryFilter) {

    categoryFilter.addEventListener(
        "change",
        function () {

            const category =
                this.value;

            const filteredEvents =
                category === "All"
                    ? communityEvents
                    : communityEvents.filter(
                        event =>
                            event.category === category
                    );

            renderEvents(
                filteredEvents
            );
        }
    );
}

const searchBox =
    document.getElementById(
        "searchBox"
    );

if (searchBox) {

    searchBox.addEventListener(
        "keydown",
        function () {

            const searchText =
                this.value.toLowerCase();

            const filtered =
                communityEvents.filter(
                    event =>
                        event.name
                            .toLowerCase()
                            .includes(
                                searchText
                            )
                );

            renderEvents(
                filtered
            );
        }
    );
}

/* =========================
   EXISTING FUNCTIONS
========================= */

window.onload = function () {

    alert(
        "Page Loaded Successfully"
    );

    displayEvents();

    renderEvents();

    let saved =
        localStorage.getItem(
            "preferredEvent"
        );

    if (saved) {

        document.getElementById(
            "eventType"
        ).value = saved;

        showFee();
    }
};

window.onbeforeunload =
function () {

    return "Are you sure you want to leave?";
};

console.log("Portal Loaded");
async function loadEvents() {

    try {

        document.getElementById("loading")
        .innerHTML = "Loading...";

        const response =
        await fetch(
        "https://jsonplaceholder.typicode.com/users"
        );

        const data =
        await response.json();

        console.log(data);

        document.getElementById("loading")
        .innerHTML =
        "Events Loaded Successfully";

    }

    catch(error) {

        console.log(error);
    }
}

loadEvents();
function addNewEvent(
    name = "New Event",
    category = "General"
) {

    console.log(name, category);
}

const eventDetails = {

    name: "Music Night",
    category: "Music",
    seats: 20
};

const {
    name,
    category,
    seats: availableSeats
} = eventDetails;

console.log(
    name,
    category,
    availableSeats
);

const clonedEvents =
[
    ...communityEvents
];

console.log(clonedEvents);
const form =
document.getElementById("eventForm");

form.addEventListener("submit", function(event) {

    event.preventDefault();

    const name =
    form.elements["name"].value;

    const email =
    form.elements["email"].value;

    const selectedEvent =
    form.elements["eventType"].value;

    const error =
    document.getElementById("formError");

    error.innerHTML = "";

    if(name === "" ||
       email === "" ||
       selectedEvent === "") {

        error.innerHTML =
        "Please fill all fields";

        return;
    }

    alert(
        `${name} registered for ${selectedEvent}`
    );
});
function submitRegistration() {

    document.getElementById("apiResult")
    .innerHTML = "Sending...";

    setTimeout(() => {

        fetch(
        "https://jsonplaceholder.typicode.com/posts",
        {
            method: "POST",

            headers: {
                "Content-Type":
                "application/json"
            },

            body: JSON.stringify({
                name: "Swathika",
                event: "Workshop"
            })
        })

        .then(response =>
            response.json()
        )

        .then(data => {

            document.getElementById(
            "apiResult"
            ).innerHTML =

            "Registration Sent Successfully";
        })

        .catch(error => {

            document.getElementById(
            "apiResult"
            ).innerHTML =

            "Submission Failed";
        });

    }, 2000);
}
submitRegistration();
$(document).ready(function() {

    $("#registerBtn").click(function() {

        $(".eventCard").fadeOut(1000);

        setTimeout(function() {

            $(".eventCard").fadeIn(1000);

        }, 1000);
    });

});