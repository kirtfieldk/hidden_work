package com.worklogger_differences.worklogger;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class unitTests {

        private JdbcTemplate jdbc = new JdbcTemplate();


//        @Test
//        @DisplayName("Testing the finding differences between strings")
//        void justAnExample() {
//                DbService dbService = new DbService(jdbc);
//                FileContentTable f1 = new FileContentTable(1,"fileId","Hello world\n" +
//                        "How Are you?","1");
//                FileContentTable f2 = new FileContentTable(2,"fileId","Hello world\n" +
//                        "How Are me?","1");
//                List<String> value = dbService.findDifferenceBetweenTwoFiles(f1, f2);
//                assertEquals(value, new ArrayList<String>(){{
//                        add("How Are you?");}});
//        }
//        @Test
//        @DisplayName("Testing dif between strings with larger content")
//        void justAnExample2() {
//                DbService dbService = new DbService(jdbc);
//                FileContentTable f1 = new FileContentTable(1,
//                        "fileId",
//                        "ARG CROSS=\"false\"\n" +
//                                "ARG SYSTEMD=\"false\"\n" +
//                                "ARG GO_VERSION=1.13.8\n" +
//                                "ARG DEBIAN_FRONTEND=noninteractive\n" +
//                                "ARG VPNKIT_VERSION=0.4.0\n" +
//                                "ARG DOCKER_BUILDTAGS=\"apparmor seccomp selinux\"\n" +
//                                "\n" +
//                                "FROM golang:${GO_VERSION}-buster AS base\n" +
//                                "RUN echo 'Binary::apt::APT::Keep-Downloaded-Packages \"true\";' > /etc/apt/apt.conf.d/keep-cache\n" +
//                                "ARG APT_MIRROR\n" +
//                                "RUN sed -ri \"s/(httpredir|deb).debian.org/${APT_MIRROR:-deb.debian.org}/g\" /etc/apt/sources.list \\\n" +
//                                " && sed -ri \"s/(security).debian.org/${APT_MIRROR:-security.debian.org}/g\" /etc/apt/sources.list\n" +
//                                "ENV GO111MODULE=off\n" +
//                                "\n" +
//                                "FROM base AS criu\n" +
//                                "ARG DEBIAN_FRONTEND\n" +
//                                "# Install dependency packages specific to criu\n" +
//                                "RUN --mount=type=cache,sharing=locked,id=moby-criu-aptlib,target=/var/lib/apt \\\n" +
//                                "    --mount=type=cache,sharing=locked,id=moby-criu-aptcache,target=/var/cache/apt \\\n" +
//                                "        apt-get update && apt-get install -y --no-install-recommends \\\n" +
//                                "            libcap-dev \\\n" +
//                                "            libnet-dev \\\n" +
//                                "            libnl-3-dev \\\n" +
//                                "            libprotobuf-c-dev \\\n" +
//                                "            libprotobuf-dev \\\n" +
//                                "            protobuf-c-compiler \\\n" +
//                                "            protobuf-compiler \\\n" +
//                                "            python-protobuf",
//                        "1");
//                FileContentTable f2 = new FileContentTable(2,
//                        "fileId",
//                        "ARG CROSS=\"false\"\n" +
//                                "ARG SYSTEMD=\"false\"\n" +
//                                "ARG GO_VERSION=1.13.8\n" +
//                                "ARG DEBIAN_FRONTEND=noninteractive\n" +
//                                "ARG VPNKIT_VERSION=0.4.0\n" +
//                                "ARG DOCKER_BUILDTAGS=\"apparmor seccomp selinux\"\n" +
//                                "\n" +
//                                "FROM golang:${GO_VERSION}-buster AS base\n" +
//                                "RUN echo 'Binary::apt::APT::Keep-Downloaded-Packages \"true\";' > /etc/apt/apt.conf.d/keep-cache\n" +
//                                "ARG APT_MIRROR\n" +
//                                "RUN sed -ri \"s/(httpredir|deb).debian.org/${APT_MIRROR:-deb.debian.org}/g\" /etc/apt/sources.list \\\n" +
//                                " && sed -ri \"s/(security).debian.org/${APT_MIRROR:-security.debian.org}/g\" /etc/apt/sources.list\n" +
//                                "ENV GO111MODULE=off\n" +
//                                "\n" +
//                                "FROM base AS criu\n" +
//                                "ARG DEBIAN_FRONTEND\n" +
//                                "# Install dependency packages specific to criu\n" +
//                                "RUN --mount=type=cache,sharing=locked,id=moby-criu-aptlib,target=/var/lib/apt \\\n" +
//                                "    --mount=type=cache,sharing=locked,id=moby-criu-aptcache,target=/var/cache/apt \\\n" +
//                                "        apt-get update && apt-get install -y --no-install-recommends \\\n" +
//                                "            libcap-dev \\\n" +
//                                "            libnet-dev \\\n" +
//                                "            libnl-3-dev \\\n" +
//                                "            libprotobuf-c-dev \\\n" +
//                                "            libprotobuf-dev \\\n" +
//                                "            protobuf-c-compiler \\\n" +
//                                "            protobuf-compiler \\\n" +
//                                "            python-protobuf",
//                        "1");
//                assertEquals(dbService.findDifferenceBetweenTwoFiles(f1,f2), new ArrayList<String>());
//
//        }
}
