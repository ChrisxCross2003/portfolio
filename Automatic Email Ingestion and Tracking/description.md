# Customer Email Ingester and Tracker

Disclaimer: The Excel sheet is an inactive copy of what the automation did (i.e., a snapshot from January 2025). Due to privacy, I have removed all personal information of customers on this sheet and made sure that this copy is no longer updated.

## Overview

I developed this project in early January 2025 while working as a Shift Manager at my local movie theater during the holiday break. Our theater is part of a small chain, lacking a dedicated corporate office to handle overhead customer issues. Consequently, all customer issues are directed via email to the managers, who are responsible for addressing each one promptly.

## Problem Statement

The challenge with our existing system was the difficulty in tracking which manager was responding to specific issues, especially during high-volume or busy periods, such as when our ticketing website experienced downtime.

## Solution

To streamline the process, I implemented a pipeline using Power Automate, a free feature of Office 365. Here's how the system works:
- **Email Integration**: Automatically detects new customer issue emails in our Outlook inbox.
- **Data Extraction**: PowerAutomate converts the email content into an HTML text file.
- **Automation**: PowerAutomate then ingests the extracted information into an Excel sheet, regardless of whether the computer is on or the Excel sheet is open.

## Features

- **Issue Tracking**: Managers can view new issues in the Excel sheet, mark their assignment, add notes, and update the status.
- **Dashboard**: Created an automatic dashboard within the Excel sheet to monitor:
  - Number of new issues.
  - Assigned managers.
  - Most common issue reasons.

## Benefits

This system significantly enhanced our management team's efficiency in handling customer issues, particularly during high-impact incidents like website outages or billing errors. Our reviews saw a significant increase in praise to management's speed of service!
