# Dynamic Federal Reserve Dashboard

## Why I Built This

This started as a personal project to help me track inflation, unemployment, and interest rates over time. I was curious to see if any patterns would emerge when comparing the three, and I wanted a quick, intuitive way to explore that data without having to write new code every time. Excel felt like the perfect tool to make this approachable and interactive.

## The Data

I used publicly available datasets on Federal Reserve interest rates, unemployment rates, and inflation. The data stretches from 1955 to 2024 and includes both monthly and yearly values. After cleaning and organizing the data, I calculated annual averages and aligned monthly data by year for a smoother user experience.

## How The Dashboard Works

- There are three tabs on the Excel sheet: Dashboard, Dashboard Data, and Data.
- Data is the raw public data I collected from the Federal Reserve.
- Dashboard Data uses Pivot Tables and other tools to summarize the raw data to show on the dashboard.
- Dashboard is the visual representation of the summarized data in an interactive way.

Let's talk about the dashboard itself.
- The **left panel** shows overall yearly trends for each variable—Unemployment Rate, Inflation, and Interest Rates—from 1955 to 2024.
- The **middle panel** updates automatically to show monthly values for whichever year the user selects.
- The **right panel** includes a dropdown menu where users can choose any year between 1955 and 2024. Invalid inputs are blocked with a warning message.
- Below the dropdown, yearly averages update dynamically for all three variables.
- The sheet is protected so users can’t accidentally delete or modify charts or data—only the dropdown is interactive (unless they choose to unprotect the sheet manually).

## What I Learned

This project challenged me to push Excel beyond its typical use. I wanted to see how far I could go in building a tool that felt interactive, clean, and data-driven—without relying on external software. It was also a reminder of how powerful familiar tools like Excel can be when used creatively.

---
