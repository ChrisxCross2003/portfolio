# Movie Theater Review Sentiment Analysis

## Overview

I developed this project as an academic project with a team of two other students. We used Excel and a Python Notebook file to compare customer sentiment across Google Reviews for three movie theater chains of varying sizes: Violet Crown (small), Alamo Drafthouse (medium), and Regal Cinemas (large). The goal was to extract, analyze, and compare public perception using natural language processing techniques.

## Problem Statement

We wanted to explore whether larger theater chains receive more positive reviews due to greater brand recognition. Additionally, we examined how reviewer type (Google Local Guides vs. regular users) might influence the tone and content of reviews.

## Analysis Plan

We scraped nearly 3,000 Google Reviews across all three theater chains and used the TextBlob library to conduct Sentiment Analysis, capturing polarity and subjectivity for each review. We then ran statistical tests, including ANOVA and post-hoc comparisons, to evaluate differences in average sentiment scores and star ratings between the theaters.

## Combining data

After sentiment analysis, we combined the data back into Excel via an export script on the Python Notebook. This allowed us to review and analyze sentiment scores alongside metadata such as review date, user type, and star rating, making it easier to visualize trends and prepare for statistical analysis.

## Successful Pipeline Creation

Overall, we were able to:
- Create a reliable scraping pipeline to extract structured review data from Google.
- Apply sentiment analysis using TextBlob to measure tone and subjectivity.
- Conduct statistical tests (ANOVA, post-hoc) to identify significant differences in sentiment and rating across theater sizes.
- Export and organize results for further visualization and interpretation.
- Results can be found in this folder.
---
