package com.example.cattinder.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * This is a distributed logger that allows you to hook up many different
 * types of logging into a single logging call.
 *
 * The logger will automatically attach a log tag for you
 * specifying the class and thread ID the log call was made on.
 *
 * To create a type of logging you plant a Tree by calling
 * {@code Logger.plant()}.
 *
 * Copyright 2015 Christopher Perry
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class Logger {

    // TODO: allow specifying a custom tag per tree.
    private static final String LOG_TAG = "CatTinder";
    private static final String FORMAT_LOG_MESSAGE = "[%s]: %s";
    private static final List<Tree> FOREST = new ArrayList<Tree>();

    /**
     * Planting a Tree adds logging. You MUST plant
     * a tree if you want to see any logs.
     */
    public static void plant(Tree tree) {
        FOREST.add(tree);
    }

    /**
     * Clear all the trees, which
     * turns all logging off; after which,
     * you must re-plant.
     */
    public static void clearForest() {
        FOREST.clear();
    }

    ////////////////////////////////////////////////////////
    // Auto tagged log calls.
    //
    // All args are optional, if any are supplied
    // the usual Java String format rules apply.
    ////////////////////////////////////////////////////////

    public static void verbose(String message, Object... args) {
        String tag = createTag();
        message = formatString(message, args);

        for (Tree tree : FOREST) {
            tree.verbose(tag, message);
        }
    }

    public static void debug(String message, Object... args) {
        String tag = createTag();
        message = formatString(message, args);

        for (Tree tree : FOREST) {
            tree.debug(tag, message);
        }
    }

    public static void info(String message, Object... args) {
        String tag = createTag();
        message = formatString(message, args);

        for (Tree tree : FOREST) {
            tree.info(tag, message);
        }
    }

    public static void warn(String message, Object... args) {
        String tag = createTag();
        message = formatString(message, args);

        for (Tree tree : FOREST) {
            tree.warn(tag, message);
        }
    }

    public static void error(String message, Object... args) {
        String tag = createTag();
        message = formatString(message, args);

        for (Tree tree : FOREST) {
            tree.error(tag, message);
        }
    }

    public static void stackTrace() {
        String tag = createTag();
        String message = getStackTrace();
        for (Tree tree : FOREST) {
            tree.stackTrace(tag, message);
        }
    }

    private static String getStackTrace() {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            sb.append(element.toString())
              .append("\n");
        }
        return sb.toString();
    }

    private static String formatString(String message, Object... args) {
        return args.length == 0
               ? message
               : String.format(message, args);
    }

    private static String createTag() {
        String className = new Throwable().getStackTrace()[2].getClassName();
        return className.substring(className.lastIndexOf('.') + 1);
    }

    private static String logtagWithThreadId() {
        return LOG_TAG + " {" + Thread.currentThread()
                                      .getId() + "}";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    // Trees
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public interface Tree {

        void verbose(String tag, String message);

        void info(String tag, String message);

        void debug(String tag, String message);

        void warn(String tag, String message);

        void error(String tag, String message);

        void stackTrace(String tag, String message);
    }

    // Useful for extending
    static class HollowTree implements Tree {

        @Override
        public void verbose(String tag, String message) {
        }

        @Override
        public void info(String tag, String message) {
        }

        @Override
        public void debug(String tag, String message) {
        }

        @Override
        public void warn(String tag, String message) {
        }

        @Override
        public void error(String tag, String message) {
        }

        @Override
        public void stackTrace(String tag, String message) {
        }
    }

    /**
     * Logs to ADB. All logs are on.
     */
    public static class AndroidTree implements Tree {

        @Override
        public void verbose(String tag, String message) {
            Log.v(logtagWithThreadId(), String.format(FORMAT_LOG_MESSAGE, tag, message));
        }

        @Override
        public void info(String tag, String message) {
            Log.i(logtagWithThreadId(), String.format(FORMAT_LOG_MESSAGE, tag, message));
        }

        @Override
        public void debug(String tag, String message) {
            Log.d(logtagWithThreadId(), String.format(FORMAT_LOG_MESSAGE, tag, message));
        }

        @Override
        public void warn(String tag, String message) {
            Log.w(logtagWithThreadId(), String.format(FORMAT_LOG_MESSAGE, tag, message));
        }

        @Override
        public void error(String tag, String message) {
            Log.e(logtagWithThreadId(), String.format(FORMAT_LOG_MESSAGE, tag, message));
        }

        @Override
        public void stackTrace(String tag, String message) {
            Log.e(logtagWithThreadId(), String.format(FORMAT_LOG_MESSAGE, tag, message));
        }
    }

    /**
     * Only logs errors, all other logs are turned off
     */
    public static class AndroidErrorTree extends HollowTree {

        @Override
        public void error(String tag, String message) {
            Log.e(logtagWithThreadId(), String.format(FORMAT_LOG_MESSAGE, tag, message));
        }
    }
}


