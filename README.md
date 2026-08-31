# ArrayLists: Song Downloads and Product Reviews

**Unit 3 — ArrayList** · Pairs with lecture 3.3 ArrayList · CED 4.8–4.10

Two classes, each built around an `ArrayList` of objects. `SongList` tracks
how many times each song on a music site has been downloaded: look a song up
by title, then process a batch of new downloads with the classic
*update-or-append* pattern. `ReviewCollection` stores product reviews: add a
review while keeping a duplicate-free list of product names, then count the
"good" reviews for a product by scanning their text for the word *best*.

`SongList` is adapted from the 2013 AP Computer Science A free-response
question *MusicDownloads* (Question 1). `ReviewCollection` is adapted from
the *ReviewCollector* question on the College Board's 2020 AP CSA Practice
Exam. Both are exam-shaped: the search-then-act loop over an `ArrayList` is
the single most common FRQ pattern in Unit 4.

## What you are given
| File | Status | Purpose |
|---|---|---|
| `src/main/java/SongList.java` | **you complete this** | `getSongInfo`, `updateDownloads` |
| `src/main/java/ReviewCollection.java` | **you complete this** | `addReview`, `getNumGoodReviews` |
| `src/main/java/SongInfo.java` | provided | a title and a download count that starts at 1 |
| `src/main/java/ProductReview.java` | provided | a product name and a review text |
| `src/test/java/*Test.java` | provided | the autograder's tests — read them |

Both classes also have provided constructors and getters that the tests use.
Leave them alone.

## What to write

| Method | Points | What it does |
|---|---|---|
| `SongList.getSongInfo(String title)` | 20 | the `SongInfo` with that title, or `null` |
| `SongList.updateDownloads(ArrayList<String> titles)` | 30 | for each title: increment if present, else append a new `SongInfo` |
| `ReviewCollection.addReview(ProductReview prodReview)` | 20 | append the review; append the product name only if new |
| `ReviewCollection.getNumGoodReviews(String prodName)` | 30 | how many of that product's reviews contain the word *best* |

### `getSongInfo(String title)`
Loop over `downloadList`. Return the first `SongInfo` whose `getTitle()`
`.equals(title)`. If the loop finishes with no match, return `null`. Titles
are unique and case-sensitive. The list must not change. Return the object
that is *in* the list — the tests check with `assertSame`.

```
downloadList:  0 "Little Man" 5   1 "Yam Yam" 2   2 "Tree Hugger" 4
getSongInfo("Yam Yam")  -> the object at index 1
getSongInfo("Vampire")  -> null
```

### `updateDownloads(ArrayList<String> titles)`
For each title in `titles`, **in order**:
- call `getSongInfo(title)`;
- if it returns a `SongInfo`, call `incrementTimesDownloaded()` on it;
- if it returns `null`, `add(new SongInfo(title))` to the **end** of
  `downloadList` (a new `SongInfo` starts at 1 download).

You must use `getSongInfo` here — do not write the search twice.

Start with the list above and
`titles = {"Hey Jude", "Daria", "Yam Yam", "Russian", "Tree Hugger", "Hey Jude"}`:
```
 0 "Little Man" 5   1 "Yam Yam" 3   2 "Tree Hugger" 5
 3 "Hey Jude"   2   4 "Daria"   1   5 "Russian"     1
```
`"Hey Jude"` appears twice: the first time it is appended with count 1, the
second time `getSongInfo` finds it and bumps it to 2. That is the trap — if
you decide "new or not" for every title *before* you start adding, you get
two `"Hey Jude"` entries. Handle one title at a time, all the way through.

### `addReview(ProductReview prodReview)`
Two steps:
1. `reviewList.add(prodReview)` — always.
2. If `prodReview.getName()` is **not already in** `productList`, add it to
   the end of `productList`. Product names are exact and case-sensitive.

Adding reviews for `HUE Lights`, `Thousand Helmet`, `HUE Lights`, `SAMSUNG TV`
gives `reviewList` with four entries and
`productList = {"HUE Lights", "Thousand Helmet", "SAMSUNG TV"}`.

### `getNumGoodReviews(String prodName)`
Count the reviews in `reviewList` whose product name equals `prodName` **and**
whose text contains the word `best`. Return 0 if there are none — including
when the product has no reviews at all or is not in the collection.

The rule for "contains the word best":
- case does not matter: `best`, `Best`, `BEST` all count;
- it must be the **whole word**: `Dumbest`, `asbestos`, `bestiary`, `bestest`
  do **not** count;
- `best` may be the first or last word of the review;
- reviews never have punctuation glued to the word (you will see
  `"safe, best purchase"` but never `"the best."`).

The technique — lower-case the text, pad it with a space on each side, and
look for `" best "` with spaces:
```java
String padded = " " + review.toLowerCase() + " ";
if (padded.indexOf(" best ") >= 0) { ... }
```
The padding is what makes a leading or trailing `best` match. Plain
`indexOf("best")` fails the tests because it counts `Dumbest`.

## Examples

`ReviewCollection` with these reviews:
| Product | Review | good? |
|---|---|---|
| HUE Lights | `Best lights out there` | yes |
| Thousand Helmet | `Kept my head safe, best purchase ever` | yes |
| HUE Lights | `Dumbest app ever` | no — `Dumbest` |
| Mythos Book | `This book has a horrible bestiary collection` | no — `bestiary` |
| Dyson v12 | `This was the best vacuum` | yes |
| Thousand Helmet | `Kept my head safe` | no |
| Dyson v12 | `Has a great asbestos filter` | no — `asbestos` |
| YONEX Racquet | `This is the best` | yes — last word |
| HUE Lights | `These light are the best for a smart house` | yes |
| YONEX Racquet | `BEST racquet but overpriced` | yes — caps |
| YONEX Birdies | `Worth the price` | no |

| Call | Result |
|---|---|
| `getNumGoodReviews("HUE Lights")` | `2` |
| `getNumGoodReviews("Thousand Helmet")` | `1` |
| `getNumGoodReviews("Mythos Book")` | `0` |
| `getNumGoodReviews("YONEX Racquet")` | `2` |
| `getNumGoodReviews("SAMSUNG TV")` (no reviews) | `0` |

## Running the tests
`mvn test` runs everything; `mvn test -Dtest=<ClassName>` runs one rubric line.

| Test class | Rubric line | Points |
|---|---|---|
| `GetSongInfoTest` | `SongList.getSongInfo` | 20 |
| `UpdateDownloadsTest` | `SongList.updateDownloads` | 30 |
| `AddReviewTest` | `ReviewCollection.addReview` | 20 |
| `GetNumGoodReviewsTest` | `ReviewCollection.getNumGoodReviews` | 30 |

The autograder awards a rubric line only when every test in that class passes.

## Suggested order
1. `getSongInfo` — a linear search over an `ArrayList`. Run
   `GetSongInfoTest`.
2. `updateDownloads` — one loop, one `getSongInfo` call, one `if/else`. Trace
   the `"Hey Jude"` example by hand first. Run `UpdateDownloadsTest`.
3. `addReview` — the same "is it already there?" search, this time over
   `ArrayList<String>`. Run `AddReviewTest`.
4. `getNumGoodReviews` — filter by product, then apply the padded-space
   check. Run `GetNumGoodReviewsTest`; if `HUE Lights` gives 3 instead of 2,
   you are matching `Dumbest`.

## Rules of the road
- AP Java subset only: `ArrayList` methods `size`, `get`, `add`, `set`,
  `remove`; `String` methods `equals`, `toLowerCase`, `indexOf`, `substring`,
  `length`. `contains` (on `String` or `ArrayList`) is not on the AP quick
  reference — write the loop, it is five lines. No `String.split`, no regex,
  no `Collections.*`, no streams, no lambdas.
- Do not change method headers or provided code (`SongInfo`, `ProductReview`,
  the constructors and getters).
- Do not touch `src/test`, `pom.xml`, `grading.json`, or `.github`. The
  autograder checks that they are byte-identical to the template before it
  runs a single test; if they differ it stops and awards nothing, and the
  change shows up in the roster.
- `updateDownloads` must call `getSongInfo`. Reusing a method you already
  wrote is the point.
